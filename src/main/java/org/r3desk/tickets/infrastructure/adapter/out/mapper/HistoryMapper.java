package org.r3desk.tickets.infrastructure.adapter.out.mapper;

import org.r3desk.tickets.domain.model.TicketStatusHistory;
import org.r3desk.tickets.infrastructure.adapter.out.entity.TicketStatusHistoryEntity;

public class HistoryMapper {

    public static TicketStatusHistory toDomain(TicketStatusHistoryEntity entity) {
        var domain = new TicketStatusHistory();
        domain.setId(entity.getId());
        domain.setPreviousStatus(entity.getPreviousStatus());
        domain.setNewStatus(entity.getNewStatus());
        domain.setReason(entity.getReason());
        domain.setChangedBy(UserMapper.toDomain(entity.getChangedBy()));
        domain.setChangedAt(entity.getChangedAt());
        return domain;
    }

    public static TicketStatusHistoryEntity toEntity(TicketStatusHistory domain) {
        var entity = new TicketStatusHistoryEntity();
        entity.setId(domain.getId());
        entity.setPreviousStatus(domain.getPreviousStatus());
        entity.setNewStatus(domain.getNewStatus());
        entity.setReason(domain.getReason());
        if(domain.getChangedBy() != null) {
            entity.setChangedBy(UserMapper.toEntity(domain.getChangedBy()));
        }
        entity.setChangedAt(domain.getChangedAt());
        return entity;
    }
}
