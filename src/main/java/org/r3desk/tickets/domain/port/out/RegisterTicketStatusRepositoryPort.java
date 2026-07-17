package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.domain.model.TicketStatusHistory;

public interface RegisterTicketStatusRepositoryPort {
    TicketStatusHistory execute(TicketStatusHistory history, Long ticketId);
}
