package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.TicketStatusHistory;
import org.r3desk.tickets.domain.port.in.ChangeTicketStatusUseCase;
import org.r3desk.tickets.domain.port.out.RegisterTicketStatusRepositoryPort;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;

import java.time.LocalDateTime;

@ApplicationScoped
public class ChangeTicketStatusService implements ChangeTicketStatusUseCase {

    private final RegisterTicketStatusRepositoryPort registerTicketStatusRepositoryPort;
    private final TicketRepositoryPort ticketRepositoryPort;

    public ChangeTicketStatusService (RegisterTicketStatusRepositoryPort registerTicketStatusRepositoryPort,
                                      TicketRepositoryPort ticketRepositoryPort) {
        this.registerTicketStatusRepositoryPort = registerTicketStatusRepositoryPort;
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    @Transactional
    public TicketStatusHistory execute(TicketStatusHistory status, Long ticketId) {
        var fecha = LocalDateTime.now();
        status.setChangedAt(fecha);
        var newStatus = registerTicketStatusRepositoryPort.execute(status, ticketId);
        ticketRepositoryPort.alignStatus(status.getNewStatus(), ticketId, fecha);
        return newStatus;
    }
}
