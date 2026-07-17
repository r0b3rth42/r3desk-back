package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.Grupo;
import org.r3desk.tickets.domain.port.in.GrupoUseCase;
import org.r3desk.tickets.domain.port.out.GrupoRepositoryPort;

import java.util.List;

@ApplicationScoped
public class GrupoService implements GrupoUseCase {

    private final GrupoRepositoryPort port;

    public GrupoService(GrupoRepositoryPort port) {
        this.port = port;
    }

    @Override
    public List<Grupo> findALl() {
        return port.lista();
    }

    @Override
    public List<Grupo> findByArea(Long areaId) {
        return port.findByArea(areaId);
    }

    @Override
    @Transactional
    public void create(Grupo domain) {
        port.create(domain);
    }

    @Override
    @Transactional
    public void modify(Grupo domain) {
        port.modify(domain);
    }
}
