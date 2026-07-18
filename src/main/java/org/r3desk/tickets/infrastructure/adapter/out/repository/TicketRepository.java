package org.r3desk.tickets.infrastructure.adapter.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.Comment;
import org.r3desk.tickets.domain.model.Resolution;
import org.r3desk.tickets.domain.model.Ticket;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;
import org.r3desk.tickets.infrastructure.adapter.out.entity.CommentEntity;
import org.r3desk.tickets.infrastructure.adapter.out.entity.TicketEntity;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.CommentMapper;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.ResolutionMapper;
import org.r3desk.tickets.infrastructure.adapter.out.mapper.TicketMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<TicketEntity>, TicketRepositoryPort {

    @Override
    public Ticket execute(Ticket domain) {
        var entity = TicketMapper.toEntity(domain);
        persist(entity);
        flush();
        return TicketMapper.toDomain(entity);
    }

    @Override
    public List<Ticket> getAll() {
        var lista = listAll();
        return lista.stream()
                .map(TicketMapper::toDomainList)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket findByCode(String code) {
        return find("code", code).firstResultOptional()
                .map(TicketMapper::toDomain)
                .orElseThrow();
    }

    @Override
    public List<Ticket> ffindByAssignedUser(String userId) {
        return null;
    }

    @Override
    public Ticket alignStatus(String newStatus, Long ticketId, LocalDateTime changeAt) {
        var ticketEntity = findById(ticketId);
        ticketEntity.setStatusChangedAt(changeAt);
        ticketEntity.setStatus(newStatus);
        return TicketMapper.toDomain(ticketEntity);
    }

    @Override
    public String addComment(String message, Long ticketId) {
        var comment = new CommentEntity();
        var ticket = new TicketEntity();
        ticket.setId(ticketId);
        comment.setComment(message);
        comment.setDate(LocalDateTime.now());
        comment.setTicket(ticket);

        getEntityManager().persist(comment);
        return "se añadio comentario con exito";
    }

    @Override
    public List<Comment> listComments(Long ticketId) {
        var ticket = findById(ticketId);

        return ticket.getCommentEntities()
                .stream()
                .map(CommentMapper::toDomain)
                .toList();
    }

    @Override
    public Resolution saveResolution(Resolution domain, Long ticketId) {
        var entity = ResolutionMapper.toEntity(domain);
        var ticketEntity = findById(ticketId);
        entity.setTicket(ticketEntity);
        getEntityManager().persist(entity);
        return ResolutionMapper.toDomain(entity);
    }

    @Override
    public long countAssignedTickets(Long userId){

        return count(
                "assignedTo.id",
                userId
        );

    }


}
