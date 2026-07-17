package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.model.User;
import org.r3desk.tickets.infrastructure.adapter.out.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {

        var domain = new User();
        domain.setId(entity.getId());
        domain.setUsername(entity.getNombre());
        domain.setRole(entity.getRole());
        return domain;
    }

    public static UserEntity toEntity(User domain) {
        var entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getUsername());
        entity.setRole(domain.getRole());
        return entity;
    }
}
