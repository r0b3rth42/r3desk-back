package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.TicketStatusHistory;

public interface ChangeTicketStatusUseCase {
    TicketStatusHistory execute(TicketStatusHistory status, Long ticketId);
}
