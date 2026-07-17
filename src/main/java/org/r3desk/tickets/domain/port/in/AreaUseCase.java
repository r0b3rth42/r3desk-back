package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.domain.model.Area;

import java.util.List;

public interface AreaUseCase {

    List<Area> list();
    void create(Area domain);
    void modify(Area domain);
}
