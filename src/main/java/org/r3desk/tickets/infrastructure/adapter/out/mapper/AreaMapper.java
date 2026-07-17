package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.model.Area;
import org.r3desk.tickets.infrastructure.adapter.out.entity.AreaEntity;

public class AreaMapper {

    public static Area toDomain(AreaEntity entity) {
        var domain = new Area();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        return domain;
    }

    public static AreaEntity toEntity(Area domain) {
        var entity = new AreaEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }
}
