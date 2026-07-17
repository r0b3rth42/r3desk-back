package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.port.in.DashboardUseCase;
import org.r3desk.tickets.domain.port.out.DashboardRepositoryPort;
import org.r3desk.tickets.presentation.dto.DashboardResponse;
import org.r3desk.tickets.presentation.dto.DashboardResume;

@ApplicationScoped
public class DashboardService implements DashboardUseCase {

    DashboardRepositoryPort dashboardRepositoryPort;

    public DashboardService( DashboardRepositoryPort dashboardRepositoryPort ) {
        this.dashboardRepositoryPort = dashboardRepositoryPort;
    }

    @Override
    public DashboardResponse dashboard() {
        return dashboardRepositoryPort.getFullDashboard();
    }

    @Override
    public DashboardResume resume() {
        return dashboardRepositoryPort.getResume();
    }
}
