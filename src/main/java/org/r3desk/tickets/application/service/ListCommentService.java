package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.Comment;
import org.r3desk.tickets.domain.port.in.LIstCommentUseCase;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;

import java.util.List;

@ApplicationScoped
public class ListCommentService implements LIstCommentUseCase {

    private final TicketRepositoryPort ticketRepositoryPort;

    public ListCommentService(TicketRepositoryPort ticketRepositoryPort){
        this.ticketRepositoryPort = ticketRepositoryPort;
    }
    @Override
    public List<Comment> listByTicket(Long ticketId) {
        return ticketRepositoryPort.listComments(ticketId);
    }
}
