package org.r3desk.tickets.presentation.dto;

public class SlaRiskDto {

    private String code;
    private String priority;
    private String assignedTo;
    private String timeElapsed; // O minutos restantes calculados

    public SlaRiskDto(String code, String priority, String assignedTo, String timeElapsed) {
        this.code = code;
        this.priority = priority;
        this.assignedTo = assignedTo;
        this.timeElapsed = timeElapsed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}