package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.Ticket;
import org.r3desk.tickets.domain.model.TicketStatusHistory;
import org.r3desk.tickets.domain.port.in.CreateTicketUseCase;
import org.r3desk.tickets.domain.port.out.*;

import java.time.LocalDateTime;

@ApplicationScoped
public class CreateTicketService implements CreateTicketUseCase {


    private final TicketRepositoryPort repository;
    private final RegisterTicketStatusRepositoryPort registerTicketStatusRepositoryPort;
    private final TicketCodeGeneratorPort codeGeneratorPort;

    public CreateTicketService(TicketRepositoryPort repository,
                               RegisterTicketStatusRepositoryPort registerTicketStatusRepositoryPort,
                               TicketCodeGeneratorPort codeGeneratorPort) {
        this.repository = repository;
        this.registerTicketStatusRepositoryPort = registerTicketStatusRepositoryPort;
        this.codeGeneratorPort = codeGeneratorPort;
    }

    @Override
    @Transactional
    public Ticket execute(Ticket domain) {
        domain.setStatus("REGISTERED");
        domain.setCreated(LocalDateTime.now());
        domain.setCode(codeGeneratorPort.generate());
        var registered = this.repository.execute(domain);
        var status = new TicketStatusHistory();
        status.setNewStatus(domain.getStatus());
        status.setChangedAt(LocalDateTime.now());
        status.setChangedBy(domain.getRequester());

        this.registerTicketStatusRepositoryPort.execute(status, registered.getId());
        return registered;
    }
}
