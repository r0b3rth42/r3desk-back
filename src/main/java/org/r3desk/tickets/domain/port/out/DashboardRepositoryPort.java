package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.presentation.dto.DashboardResponse;
import org.r3desk.tickets.presentation.dto.DashboardResume;

public interface DashboardRepositoryPort {

    DashboardResume getResume();
    DashboardResponse getFullDashboard();
}
