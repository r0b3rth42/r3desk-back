package org.r3desk.tickets.domain.dto;

import org.r3desk.tickets.domain.model.User;

public record LoginResult(
        String token,
        User user
) {
}