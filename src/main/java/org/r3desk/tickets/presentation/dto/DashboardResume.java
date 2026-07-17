package org.r3desk.tickets.presentation.dto;

public class DashboardResume {

    private long total;
    private long openTickets;
    private long closedToday;
    private long sla;
    private long critical;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getOpenTickets() {
        return openTickets;
    }

    public void setOpenTickets(long openTickets) {
        this.openTickets = openTickets;
    }

    public long getClosedToday() {
        return closedToday;
    }

    public void setClosedToday(long closedToday) {
        this.closedToday = closedToday;
    }

    public long getSla() {
        return sla;
    }

    public void setSla(long sla) {
        this.sla = sla;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }
}
