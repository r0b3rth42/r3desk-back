package org.r3desk.tickets.infrastructure.adapter.out.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tickets")
public class

TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String title;
    private String description;
    private String status;
    private String priority;

    @ManyToOne
    private AreaEntity sourceAreaEntity;
    @ManyToOne
    private AreaEntity targetAreaEntity;
    @ManyToOne
    private GrupoEntity sourceGroupEntity;
    @ManyToOne
    private GrupoEntity targetGroupEntity;


    @ManyToOne
    @JoinColumn(name = "requester_id")
    private UserEntity requester;
    @ManyToOne
    @JoinColumn(name = "assignedto_id")
    private UserEntity assignedTo;


    @OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TicketStatusHistoryEntity> histories;

    @OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CommentEntity> commentEntities;
    private LocalDateTime created;
    private LocalDateTime statusChangedAt;

    @OneToOne(mappedBy = "ticket")
    private ResolutionEntity resolutionEntity;

    // getters y setters



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public AreaEntity getSourceAreaEntity() {
        return sourceAreaEntity;
    }

    public void setSourceAreaEntity(AreaEntity sourceAreaEntity) {
        this.sourceAreaEntity = sourceAreaEntity;
    }

    public AreaEntity getTargetAreaEntity() {
        return targetAreaEntity;
    }

    public void setTargetAreaEntity(AreaEntity targetAreaEntity) {
        this.targetAreaEntity = targetAreaEntity;
    }

    public GrupoEntity getSourceGroupEntity() {
        return sourceGroupEntity;
    }

    public void setSourceGroupEntity(GrupoEntity sourceGroupEntity) {
        this.sourceGroupEntity = sourceGroupEntity;
    }

    public GrupoEntity getTargetGroupEntity() {
        return targetGroupEntity;
    }

    public void setTargetGroupEntity(GrupoEntity targetGroupEntity) {
        this.targetGroupEntity = targetGroupEntity;
    }

    public UserEntity getRequester() {
        return requester;
    }

    public void setRequester(UserEntity requester) {
        this.requester = requester;
    }

    public UserEntity getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserEntity assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Set<TicketStatusHistoryEntity> getHistories() {
        return histories;
    }

    public void setHistories(Set<TicketStatusHistoryEntity> histories) {
        this.histories = histories;
    }

    public Set<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(Set<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public ResolutionEntity getResolutionEntity() {
        return resolutionEntity;
    }

    public void setResolutionEntity(ResolutionEntity resolutionEntity) {
        this.resolutionEntity = resolutionEntity;
    }

    public LocalDateTime getStatusChangedAt() {
        return statusChangedAt;
    }

    public void setStatusChangedAt(LocalDateTime statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }
}
