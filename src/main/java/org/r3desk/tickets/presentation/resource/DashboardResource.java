package org.r3desk.tickets.presentation.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.r3desk.tickets.domain.port.in.DashboardUseCase;
import org.r3desk.tickets.presentation.dto.DashboardResponse;

@Path("/api/dashboard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {


    private final JsonWebToken jwt;
    private final DashboardUseCase dashboardUseCase;

    public DashboardResource(DashboardUseCase dashboardUseCase,
                             JsonWebToken jsonWebToken) {
        this.dashboardUseCase = dashboardUseCase;
        this.jwt = jsonWebToken;
    }

    @GET
    public Response listComment(@PathParam("ticketId")Long ticketId) {
        var resp = dashboardUseCase.dashboard();
        return Response
                .ok(resp)
                .build();
    }
}
