package org.r3desk.tickets.infrastructure.adapter.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.Grupo;
import org.r3desk.tickets.domain.port.out.GrupoRepositoryPort;
import org.r3desk.tickets.infrastructure.adapter.out.entity.GrupoEntity;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.AreaMapper;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.GrupoMapper;

import java.util.List;

@ApplicationScoped
public class GrupoRepository implements PanacheRepository<GrupoEntity>, GrupoRepositoryPort {


    @Override
    public List<Grupo> findByArea(Long areaId) {
        return list("area.id", areaId).stream()
                .map(GrupoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Grupo> lista() {
        return listAll()
                .stream()
                .map(GrupoMapper::toDomain)
                .toList();
    }

    @Override
    public void create(Grupo domain) {
        persist(GrupoMapper.toEntity(domain));
    }

    @Override
    public void modify(Grupo domain) {
        var entity = findById(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setArea(AreaMapper.toEntity(domain.getArea()));
        entity.setStatus(domain.getStatus());
    }
}
