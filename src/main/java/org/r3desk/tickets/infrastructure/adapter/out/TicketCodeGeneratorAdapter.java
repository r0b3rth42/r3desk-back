package org.r3desk.tickets.infrastructure.adapter.out;

import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.port.out.TicketCodeGeneratorPort;

import java.util.UUID;

@ApplicationScoped
public class TicketCodeGeneratorAdapter implements TicketCodeGeneratorPort {

    @Override
    public String generate() {
        return "INC-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
