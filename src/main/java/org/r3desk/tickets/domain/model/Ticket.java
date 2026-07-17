package org.r3desk.tickets.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.r3desk.tickets.domain.enums.Priority;


import java.time.LocalDateTime;
import java.util.List;

public class Ticket {

    private Long id;

    private String code;
    private String title;
    private String description;
    private String status;
    private Priority priority;

    //area
    private Area sourceArea;
    private Area targetArea;

    //grupo
    private Grupo sourceGroup;
    private Grupo targetGroup;

    // nombres
    private User requester;
    private User assignedTo;

    //history status
    private List<TicketStatusHistory> histories;

    //comments
    private List<Comment> comments;

    //fecha
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    //solucion
    private Resolution resolution;

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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Area getSourceArea() {
        return sourceArea;
    }

    public void setSourceArea(Area sourceArea) {
        this.sourceArea = sourceArea;
    }

    public Area getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(Area targetArea) {
        this.targetArea = targetArea;
    }

    public Grupo getSourceGroup() {
        return sourceGroup;
    }

    public void setSourceGroup(Grupo sourceGroup) {
        this.sourceGroup = sourceGroup;
    }

    public Grupo getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(Grupo targetGroup) {
        this.targetGroup = targetGroup;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<TicketStatusHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<TicketStatusHistory> histories) {
        this.histories = histories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }


}
