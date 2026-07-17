package org.r3desk.tickets.domain.port.in;

public interface AddCommentUseCase {

    String execute(String message, Long ticketId);
}
