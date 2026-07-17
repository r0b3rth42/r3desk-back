package org.r3desk.tickets.presentation.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.r3desk.tickets.domain.port.in.LoginUseCase;
import org.r3desk.tickets.presentation.dto.LoginRequest;
import org.r3desk.tickets.presentation.dto.LoginResponse;
import org.r3desk.tickets.presentation.dto.UserResponse;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {



    @POST
    @Path("/login")
    public LoginResponse login(
            LoginRequest request
    ) {

        var result = new LoginResponse("clavetapp", new UserResponse("1", "robert", "irazaba", "admin"));
        return result;
    }
}
