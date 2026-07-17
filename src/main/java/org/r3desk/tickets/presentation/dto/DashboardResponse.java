package org.r3desk.tickets.presentation.dto;

import java.util.List;
import java.util.Map;

public class DashboardResponse {

    private DashboardResume resume;
    private Map<String, Long> ticketsByStatus;      // Para el Doughnut Chart
    private Map<String, Long> ticketsByPriority;    // Para el Bar Chart
    private Map<String, Long> ticketTrend;          // Para el Line Chart (Ej: "2026-07-10": 5)
    private List<SlaRiskDto> slaInRisk;             // Tabla SLA en Riesgo
    private List<LastTicketsDto> lastTickets;       // Tabla Últimos Tickets

    public DashboardResume getResume() {
        return resume;
    }

    public void setResume(DashboardResume resume) {
        this.resume = resume;
    }

    public Map<String, Long> getTicketsByStatus() {
        return ticketsByStatus;
    }

    public void setTicketsByStatus(Map<String, Long> ticketsByStatus) {
        this.ticketsByStatus = ticketsByStatus;
    }

    public Map<String, Long> getTicketsByPriority() {
        return ticketsByPriority;
    }

    public void setTicketsByPriority(Map<String, Long> ticketsByPriority) {
        this.ticketsByPriority = ticketsByPriority;
    }

    public Map<String, Long> getTicketTrend() {
        return ticketTrend;
    }

    public void setTicketTrend(Map<String, Long> ticketTrend) {
        this.ticketTrend = ticketTrend;
    }

    public List<SlaRiskDto> getSlaInRisk() {
        return slaInRisk;
    }

    public void setSlaInRisk(List<SlaRiskDto> slaInRisk) {
        this.slaInRisk = slaInRisk;
    }

    public List<LastTicketsDto> getLastTickets() {
        return lastTickets;
    }

    public void setLastTickets(List<LastTicketsDto> lastTickets) {
        this.lastTickets = lastTickets;
    }
}
