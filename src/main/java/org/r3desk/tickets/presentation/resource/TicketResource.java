package org.r3desk.tickets.presentation.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.r3desk.tickets.domain.model.Resolution;
import org.r3desk.tickets.domain.model.Ticket;
import org.r3desk.tickets.domain.model.TicketStatusHistory;
import org.r3desk.tickets.domain.port.in.*;
import org.r3desk.tickets.presentation.dto.AddCommentRequest;
import org.r3desk.tickets.presentation.dto.TicketResolvedRequest;

import java.util.List;

@Path("/api/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource{

    private final FindTicketUseCase useCase;
    private final ViewTicketUseCase viewTicketUseCase;
    private final CreateTicketUseCase createTicketUseCase;
    private final ChangeTicketStatusUseCase changeTicketStatusUseCase;
    private final AddCommentUseCase addCommentUseCase;
    private final LIstCommentUseCase lIstCommentUseCase;
    private final ResolvedTIcketUseCase resolvedTIcketUseCase;

    public TicketResource(FindTicketUseCase useCase,
                          CreateTicketUseCase createTicketUseCase,
                          ViewTicketUseCase viewTicketUseCase,
                          ChangeTicketStatusUseCase changeTicketStatusUseCase,
                          AddCommentUseCase addCommentUseCase,
                          LIstCommentUseCase lIstCommentUseCase,
                          ResolvedTIcketUseCase resolvedTIcketUseCase) {
        this.useCase = useCase;
        this.createTicketUseCase = createTicketUseCase;
        this.viewTicketUseCase = viewTicketUseCase;
        this.changeTicketStatusUseCase = changeTicketStatusUseCase;
        this.addCommentUseCase = addCommentUseCase;
        this.lIstCommentUseCase = lIstCommentUseCase;
        this.resolvedTIcketUseCase = resolvedTIcketUseCase;
    }



    @GET
    public Response find(){
        List<Ticket> tickets = useCase.find();
        return Response.ok(tickets).build();
    }

    @GET()
    @Path("/{code}")
    public Response findByCode(@PathParam("code") String code){
        return Response
                .ok(viewTicketUseCase.execute(code))
                .build();
    }

    @POST
    public Response create(Ticket dto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(createTicketUseCase.execute(dto))
                .build();
    }

    @PATCH
    @Path("/{ticketId}/status")
    public Response changeTicketStatus(@PathParam("ticketId")Long ticketId, TicketStatusHistory status) {
        var resp = changeTicketStatusUseCase.execute(status, ticketId);
        return Response
                .ok(resp)
                .build();
    }

    @GET
    @Path("/{ticketId}/comments")
    public Response listComment(@PathParam("ticketId")Long ticketId) {
        var resp = lIstCommentUseCase.listByTicket(ticketId);

        return Response
                .ok(resp)
                .build();
    }

    @POST
    @Path("/{ticketId}/comments")
    public Response addComment(@PathParam("ticketId")Long ticketId, AddCommentRequest comments) {
        var resp = addCommentUseCase.execute(comments.getMessage(), ticketId);

        return Response
                .ok(comments)
                .build();
    }

    @POST
    @Path("/{ticketId}/resolve")
    public Response resolve(@PathParam("ticketId")Long ticketId, TicketResolvedRequest request) {
        var resolved = new Resolution();
        resolved.setSummary(request.getSummary());
        resolved.setRootCause(request.getRootCause());
        var status = new TicketStatusHistory();
        var response = resolvedTIcketUseCase.execute(resolved, request.getUser(), ticketId);
        return Response
                .ok(resolved)
                .build();
    }

}
