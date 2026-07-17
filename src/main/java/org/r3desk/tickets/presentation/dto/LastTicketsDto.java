package org.r3desk.tickets.presentation.dto;

public class LastTicketsDto {

    private String code;
    private String status;
    private String priority;
    private String assignedTo;

    public LastTicketsDto(String code, String status, String priority, String assignedTo) {
        this.code = code;
        this.status = status;
        this.priority = priority;
        this.assignedTo = assignedTo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
