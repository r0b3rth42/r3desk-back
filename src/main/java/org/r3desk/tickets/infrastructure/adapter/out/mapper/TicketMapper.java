package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.enums.Priority;
import org.r3desk.tickets.domain.model.Comment;
import org.r3desk.tickets.domain.model.Ticket;
import org.r3desk.tickets.infrastructure.adapter.out.entity.TicketEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketMapper {

    public static Ticket toDomain(TicketEntity entity){
        var domain = new Ticket();
        domain.setId(entity.getId());
        domain.setCode(entity.getCode());
        domain.setDescription(entity.getDescription());
        domain.setStatus(entity.getStatus());
        domain.setPriority(mapPriority(entity.getPriority()));
        domain.setTitle(entity.getTitle());
        domain.setSourceArea(AreaMapper.toDomain(entity.getSourceAreaEntity()));
        domain.setTargetArea(AreaMapper.toDomain(entity.getTargetAreaEntity()));
        domain.setSourceGroup(GrupoMapper.toDomain(entity.getSourceGroupEntity()));
        domain.setTargetGroup(GrupoMapper.toDomain(entity.getTargetGroupEntity()));
        domain.setRequester(UserMapper.toDomain(entity.getRequester()));
        if(entity.getHistories() != null) {
            domain.setHistories(entity.getHistories().stream().map(HistoryMapper::toDomain).toList());
        }

        if(!entity.getStatus().equals("REGISTERED")){
            domain.setAssignedTo(UserMapper.toDomain(entity.getAssignedTo()));

            domain.setComments(entity.getCommentEntities().stream().map(CommentMapper::toDomain).collect(Collectors.toList()));

            if(entity.getResolutionEntity() != null){
                domain.setResolution(ResolutionMapper.toDomain(entity.getResolutionEntity()));
            }


        }
        domain.setCreated(entity.getCreated());



        return domain;
    }

    public static Ticket toDomainList(TicketEntity entity){
        var domain = new Ticket();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setCode(entity.getCode());
        domain.setSourceArea(AreaMapper.toDomain(entity.getSourceAreaEntity()));
        domain.setTargetArea(AreaMapper.toDomain(entity.getTargetAreaEntity()));
        domain.setSourceGroup(GrupoMapper.toDomain(entity.getSourceGroupEntity()));
        domain.setTargetGroup(GrupoMapper.toDomain(entity.getTargetGroupEntity()));
        domain.setStatus(entity.getStatus());
        domain.setPriority(mapPriority(entity.getPriority()));
        domain.setCreated(entity.getCreated());
        return domain;
    }



    public static TicketEntity toEntity(Ticket domain){
        var entity = new TicketEntity();
        entity.setId(domain.getId());
        entity.setCode(domain.getCode());
        entity.setDescription(domain.getDescription());
        entity.setStatus(domain.getStatus());
        entity.setPriority(domain.getPriority().name());
        entity.setTitle(domain.getTitle());
        entity.setSourceAreaEntity(AreaMapper.toEntity(domain.getSourceArea()));
        entity.setTargetAreaEntity(AreaMapper.toEntity(domain.getTargetArea()));
        entity.setSourceGroupEntity(GrupoMapper.toEntity(domain.getSourceGroup()));
        entity.setTargetGroupEntity(GrupoMapper.toEntity(domain.getTargetGroup()));
        entity.setRequester(UserMapper.toEntity(domain.getRequester()));
        entity.setCreated(domain.getCreated());

        if(!entity.getStatus().equals("REGISTERED")){
            domain.setAssignedTo(UserMapper.toDomain(entity.getAssignedTo()));

            // Usando ArrayList para evitar problemas de streams bloqueados en Jackson
            if (entity.getCommentEntities() != null) {
                var commentList = entity.getCommentEntities().stream()
                        .map(CommentMapper::toDomain)
                        .toList(); // O .collect(Collectors.toList())
                domain.setComments(new java.util.ArrayList<>(commentList));
            }

            if(entity.getResolutionEntity() != null){
                domain.setResolution(ResolutionMapper.toDomain(entity.getResolutionEntity()));
            }

            if (entity.getHistories() != null) {
                var historyList = entity.getHistories().stream()
                        .map(HistoryMapper::toDomain)
                        .toList();
                domain.setHistories(new java.util.ArrayList<>(historyList));
            }
        }

        return entity;
    }

    private static Priority mapPriority(String value) {
        return Optional.ofNullable(value)
                .map(Priority::valueOf)
                .orElse(null);
    }
}
