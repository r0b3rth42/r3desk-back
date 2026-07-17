package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.Ticket;
import org.r3desk.tickets.domain.port.in.FindTicketUseCase;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;

import java.util.List;

@ApplicationScoped
public class FindAll implements FindTicketUseCase {

    private final TicketRepositoryPort repository;

    public FindAll(TicketRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Ticket> find() {
        return repository.getAll();
    }
}
