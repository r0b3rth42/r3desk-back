package org.r3desk.tickets.domain.port.in;

import org.r3desk.tickets.presentation.dto.DashboardResponse;
import org.r3desk.tickets.presentation.dto.DashboardResume;

public interface DashboardUseCase {

    DashboardResponse dashboard();
    DashboardResume resume();
}
