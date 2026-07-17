package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.domain.model.Grupo;

import java.util.List;

public interface GrupoRepositoryPort {

    List<Grupo> findByArea(Long areaId);

    List<Grupo> lista();

    void create(Grupo domain);
    void modify(Grupo domain);
}
