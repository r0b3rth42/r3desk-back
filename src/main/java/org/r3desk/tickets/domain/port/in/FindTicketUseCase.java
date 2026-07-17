package org.r3desk.tickets.domain.port.in;

import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.Ticket;

import java.util.List;

public interface FindTicketUseCase {

    List<Ticket> find();
}
