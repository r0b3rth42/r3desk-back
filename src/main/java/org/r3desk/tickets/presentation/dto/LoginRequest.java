package org.r3desk.tickets.presentation.dto;

public record LoginRequest(
        String username,
        String password
) {
}