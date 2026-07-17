package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.Resolution;
import org.r3desk.tickets.domain.model.User;

public interface ResolvedTIcketUseCase {

    Resolution execute(Resolution request, User user, Long ticketId);
}
