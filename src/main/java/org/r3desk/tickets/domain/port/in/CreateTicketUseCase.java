package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.Ticket;

public interface CreateTicketUseCase {
    Ticket execute(Ticket domain);
}
