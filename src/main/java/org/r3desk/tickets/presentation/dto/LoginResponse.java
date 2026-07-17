package org.r3desk.tickets.presentation.dto;

public record LoginResponse(
        String accessToken,
        UserResponse user
) {
}