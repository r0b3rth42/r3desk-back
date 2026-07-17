package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.port.in.AddCommentUseCase;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;

@ApplicationScoped
public class AddCommentService implements AddCommentUseCase {

    private final TicketRepositoryPort ticketRepositoryPort;

    public AddCommentService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    @Transactional
    public String execute(String message, Long ticketId) {
        return ticketRepositoryPort.addComment(message, ticketId);
    }
}
