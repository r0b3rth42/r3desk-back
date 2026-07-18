package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.domain.model.Comment;
import org.r3desk.tickets.domain.model.Resolution;
import org.r3desk.tickets.domain.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepositoryPort {
    Ticket execute(Ticket domain);
    List<Ticket> getAll();
    Ticket findByCode(String code);

    List<Ticket> ffindByAssignedUser(String userId);

    Ticket alignStatus(String newStatus, Long ticketId, LocalDateTime changeAt);

    String addComment(String message, Long ticketId);

    List<Comment> listComments(Long ticketId);

    Resolution saveResolution(Resolution domain, Long ticketId);

    long countAssignedTickets(Long userId);
}

