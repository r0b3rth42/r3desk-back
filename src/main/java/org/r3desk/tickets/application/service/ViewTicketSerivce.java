package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.Ticket;
import org.r3desk.tickets.domain.port.in.ViewTicketUseCase;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;

@ApplicationScoped
public class ViewTicketSerivce implements ViewTicketUseCase {

    TicketRepositoryPort ticketRepository;

    public ViewTicketSerivce(TicketRepositoryPort ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket execute(String code) {
        return ticketRepository.findByCode(code);
    }
}
