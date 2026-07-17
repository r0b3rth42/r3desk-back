package org.r3desk.tickets.presentation.dto;

public record UserResponse(
        String id,
        String username,
        String fullName,
        String role
) {
}