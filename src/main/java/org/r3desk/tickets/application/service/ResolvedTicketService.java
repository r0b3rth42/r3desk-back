package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.Resolution;
import org.r3desk.tickets.domain.model.TicketStatusHistory;
import org.r3desk.tickets.domain.model.User;
import org.r3desk.tickets.domain.port.in.ResolvedTIcketUseCase;
import org.r3desk.tickets.domain.port.out.RegisterTicketStatusRepositoryPort;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;

@ApplicationScoped
public class ResolvedTicketService implements ResolvedTIcketUseCase {

    private final RegisterTicketStatusRepositoryPort registerTicketStatusRepositoryPort;
    private final TicketRepositoryPort ticketRepositoryPort;

    public ResolvedTicketService(RegisterTicketStatusRepositoryPort registerTicketStatusRepositoryPort,
                                 TicketRepositoryPort ticketRepositoryPort){
        this.registerTicketStatusRepositoryPort = registerTicketStatusRepositoryPort;
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    @Transactional
    public Resolution execute(Resolution request, User user, Long ticketId) {
        var status = new TicketStatusHistory();
        status.setNewStatus("RESOLVED");
        status.setChangedBy(user);
        registerTicketStatusRepositoryPort.execute(status, ticketId);
        return ticketRepositoryPort.saveResolution(request, ticketId);
    }
}
