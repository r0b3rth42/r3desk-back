package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.domain.model.Area;

import java.util.List;

public interface AreaRepositoryPort {

    List<Area> list();
    void create(Area domain);

    void modify(Area domain);
}
