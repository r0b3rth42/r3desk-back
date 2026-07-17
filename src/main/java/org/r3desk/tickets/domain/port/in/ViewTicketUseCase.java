package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.Ticket;

public interface ViewTicketUseCase {

    Ticket execute(String code);
}
