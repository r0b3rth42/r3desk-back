package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.Grupo;

import java.util.List;

public interface GrupoUseCase {

    List<Grupo> findALl();

    List<Grupo> findByArea(Long areaId);
    void create(Grupo domain);
    void modify(Grupo domain);
}
