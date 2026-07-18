package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.model.Grupo;
import org.r3desk.tickets.infrastructure.adapter.out.entity.GrupoEntity;

public class GrupoMapper {

    public static Grupo toDomain(GrupoEntity entity) {
        var domain = new Grupo();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setStatus(entity.getStatus());
        if(entity.getArea() != null){
            domain.setArea(AreaMapper.toDomain(entity.getArea()));
        }

        return domain;
    }

    public static GrupoEntity toEntity(Grupo domain) {
        var entity = new GrupoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setStatus(domain.getStatus());
        if(domain.getArea() != null){
            entity.setArea(AreaMapper.toEntity(domain.getArea()));
        }

        return entity;
    }
}
