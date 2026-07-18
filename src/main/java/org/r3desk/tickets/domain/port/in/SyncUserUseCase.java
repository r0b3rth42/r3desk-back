package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.User;

public interface SyncUserUseCase {

    User execute(
            String cognitoId,
            String email,
            String nombre
    );
}
