package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.domain.model.Ticket;

public interface CreateTicketRepository {
    Ticket execute(Ticket domain);
}
