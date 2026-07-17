package org.r3desk.tickets.infrastructure.adapter.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.TicketStatusHistory;
import org.r3desk.tickets.domain.port.out.RegisterTicketStatusRepositoryPort;
import org.r3desk.tickets.infrastructure.adapter.out.entity.TicketEntity;
import org.r3desk.tickets.infrastructure.adapter.out.entity.TicketStatusHistoryEntity;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.HistoryMapper;

import java.time.LocalDateTime;

@ApplicationScoped
public class StatusRepository implements PanacheRepository<TicketStatusHistoryEntity>, RegisterTicketStatusRepositoryPort {


    @Override
    public TicketStatusHistory execute(TicketStatusHistory history, Long ticketId) {
        var entity = HistoryMapper.toEntity(history);
        var ticket =
                getEntityManager().getReference(
                        TicketEntity.class,
                        ticketId
                );
        entity.setChangedByUsername(ticket.getRequester().getNombre());
        entity.setTicket(ticket);
        persist(entity);
        return HistoryMapper.toDomain(entity);
    }
}
