package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.model.Comment;
import org.r3desk.tickets.infrastructure.adapter.out.entity.CommentEntity;

public class CommentMapper {

    public static Comment toDomain(CommentEntity entity) {
        var domain = new Comment();
        domain.setId(entity.getId());
        domain.setComment(entity.getComment());
        domain.setAuthor(entity.getAuthor());
        domain.setId(entity.getId());
        domain.setDate(entity.getDate());
        return domain;
    }

    public static CommentEntity toEntity(Comment domain) {
        var entity = new CommentEntity();
        entity.setComment(domain.getComment());
        entity.setAuthor(domain.getAuthor());
        entity.setId(domain.getId());
        entity.setDate(domain.getDate());
        return entity;
    }
}
