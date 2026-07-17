package org.r3desk.tickets.infrastructure.adapter.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.Area;
import org.r3desk.tickets.domain.port.out.AreaRepositoryPort;
import org.r3desk.tickets.infrastructure.adapter.out.entity.AreaEntity;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.AreaMapper;

import java.util.List;

@ApplicationScoped
public class AreaRepository implements PanacheRepository<AreaEntity>, AreaRepositoryPort {


    @Override
    public List<Area> list() {
        return listAll().stream()
                .map(AreaMapper::toDomain)
                .toList();
    }

    @Override
    public void create(Area domain) {
        persist(AreaMapper.toEntity(domain));
    }

    @Override
    public void modify(Area domain) {
        var entity = findById(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setStatus(domain.getStatus());
    }
}
