package org.r3desk.tickets.infrastructure.adapter.out.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.r3desk.tickets.domain.port.out.DashboardRepositoryPort;
import org.r3desk.tickets.presentation.dto.DashboardResponse;
import org.r3desk.tickets.presentation.dto.DashboardResume;
import org.r3desk.tickets.presentation.dto.LastTicketsDto;
import org.r3desk.tickets.presentation.dto.SlaRiskDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class DahboardRepositoryAdapter implements DashboardRepositoryPort {

    @Inject
    EntityManager em;

    @Override
    public DashboardResume getResume() {
        Object[] row = (Object[]) em.createNativeQuery("""
            SELECT
                COUNT(*) AS total,
                SUM(CASE WHEN status <> 'CLOSED' THEN 1 ELSE 0 END) AS abiertos,
                SUM(CASE WHEN status = 'CLOSED'
                          AND DATE(statusChangedAt) = CURRENT_DATE
                         THEN 1 ELSE 0 END) AS cerrados_hoy,
                SUM(CASE WHEN priority = 'CRITICAL' THEN 1 ELSE 0 END) AS criticos
            FROM tickets
        """).getSingleResult();

        DashboardResume resume = new DashboardResume();
        resume.setTotal(((Number) row[0]).longValue());
        resume.setOpenTickets(((Number) row[1]).longValue());
        resume.setClosedToday(((Number) row[2]).longValue());
        resume.setCritical(((Number) row[3]).longValue());
        return resume;
    }

    @Override
    public DashboardResponse getFullDashboard() {
        DashboardResponse response = new DashboardResponse();

        // 1. Reutilizar tu lógica de resumen
        response.setResume(getResume());

        // 2. Tickets por Estado (Doughnut Chart)
        List<Object[]> statusRows = em.createQuery(
                        "SELECT t.status, COUNT(t) FROM TicketEntity t GROUP BY t.status", Object[].class)
                .getResultList();
        Map<String, Long> statusMap = statusRows.stream()
                .collect(Collectors.toMap(row -> (String) row[0], row -> (Long) row[1]));
        response.setTicketsByStatus(statusMap);

        // 3. Tickets por Prioridad (Bar Chart)
        List<Object[]> priorityRows = em.createQuery(
                        "SELECT t.priority, COUNT(t) FROM TicketEntity t GROUP BY t.priority", Object[].class)
                .getResultList();
        Map<String, Long> priorityMap = priorityRows.stream()
                .collect(Collectors.toMap(row -> (String) row[0], row -> (Long) row[1]));
        response.setTicketsByPriority(priorityMap);

        // 4. Tendencia de Tickets (Line Chart - Compatible con MariaDB)
        List<Object[]> trendRows = em.createNativeQuery("""
        SELECT DATE(created) as fecha, COUNT(*) as total 
        FROM tickets 
        WHERE created >= DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY)
        GROUP BY DATE(created) 
        ORDER BY fecha ASC
        """).getResultList();

        Map<String, Long> trendMap = new LinkedHashMap<>();
        for (Object[] row : trendRows) {
            trendMap.put(row[0].toString(), ((Number) row[1]).longValue());
        }
        response.setTicketTrend(trendMap);

        // 5. SLA en Riesgo (Prioridad Alta/Crítica, no cerrados, ordenados por antigüedad)
        List<Object[]> slaRows = em.createQuery("""
            SELECT t.code, t.priority, COALESCE(u.nombre, 'Sin asignar'), t.created 
            FROM TicketEntity t 
            LEFT JOIN t.assignedTo u 
            WHERE t.status <> 'CLOSED' AND t.priority IN ('HIGH', 'CRITICAL')
            ORDER BY t.created ASC
            """, Object[].class)
                .setMaxResults(5) // Top 5 críticos en riesgo
                .getResultList();

        List<SlaRiskDto> slaInRisk = slaRows.stream().map(row -> {
            // Ejemplo de cálculo simple: Podrías calcular la diferencia de tiempo aquí o enviar la fecha al front
            return new SlaRiskDto((String) row[0], (String) row[1], (String) row[2], "Verificar");
        }).collect(Collectors.toList());
        response.setSlaInRisk(slaInRisk);

        // 6. Últimos Tickets (Los 5 más recientes en crearse)
        List<Object[]> lastRows = em.createQuery("""
            SELECT t.code, t.status, t.priority, COALESCE(u.nombre, 'Sin asignar') 
            FROM TicketEntity t 
            LEFT JOIN t.assignedTo u 
            ORDER BY t.id DESC
            """, Object[].class)
                .setMaxResults(5)
                .getResultList();

        List<LastTicketsDto> lastTickets = lastRows.stream()
                .map(row -> new LastTicketsDto((String) row[0], (String) row[1], (String) row[2], (String) row[3]))
                .collect(Collectors.toList());
        response.setLastTickets(lastTickets);

        return response;
    }
}
