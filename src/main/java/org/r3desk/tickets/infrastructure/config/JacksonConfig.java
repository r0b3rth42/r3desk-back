package org.r3desk.tickets.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class JacksonConfig {
    @Produces
    public ObjectMapper objectMapper(ObjectMapper defaultMapper) {
        defaultMapper.registerModule(new JavaTimeModule());
        return defaultMapper;
    }
}
