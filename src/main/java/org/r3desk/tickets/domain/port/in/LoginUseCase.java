package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.dto.LoginResult;

public interface LoginUseCase {
    LoginResult execute(String username, String password);
}
