package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.model.Resolution;
import org.r3desk.tickets.infrastructure.adapter.out.entity.ResolutionEntity;

public class ResolutionMapper {

    public static Resolution toDomain(ResolutionEntity entity) {
        var domain = new Resolution();
        domain.setRootCause(entity.getRootCause());
        domain.setSummary(entity.getSummary());
        return domain;
    }

    public static ResolutionEntity toEntity(Resolution domain) {
        var entity = new ResolutionEntity();
        entity.setRootCause(domain.getRootCause());
        entity.setSummary(domain.getSummary());
        return entity;
    }
}
