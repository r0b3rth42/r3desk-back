package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.Comment;

import java.util.List;

public interface LIstCommentUseCase {

    List<Comment> listByTicket(Long ticketId);
}
