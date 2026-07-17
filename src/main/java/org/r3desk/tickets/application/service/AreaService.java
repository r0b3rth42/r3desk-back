package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.Area;
import org.r3desk.tickets.domain.port.in.AreaUseCase;
import org.r3desk.tickets.domain.port.out.AreaRepositoryPort;

import java.util.List;

@ApplicationScoped
public class AreaService implements AreaUseCase {

    private final AreaRepositoryPort port;

    public AreaService(AreaRepositoryPort port) {
        this.port = port;
    }


    @Override
    public List<Area> list() {
        return port.list();
    }

    @Override
    @Transactional
    public void create(Area domain) {
        port.create(domain);
    }

    @Override
    @Transactional
    public void modify(Area domain) {
        port.modify(domain);
    }
}
