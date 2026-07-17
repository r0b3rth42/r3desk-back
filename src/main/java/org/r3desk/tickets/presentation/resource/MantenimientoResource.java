package org.r3desk.tickets.presentation.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.r3desk.tickets.application.service.AreaService;
import org.r3desk.tickets.domain.model.Area;
import org.r3desk.tickets.domain.model.Grupo;
import org.r3desk.tickets.domain.port.in.AreaUseCase;
import org.r3desk.tickets.domain.port.in.GrupoUseCase;

@Path("/api/mantenimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MantenimientoResource {

    private final AreaUseCase useCase;
    private final GrupoUseCase grupoUseCase;

    public MantenimientoResource(AreaUseCase useCase,
                                 GrupoUseCase grupoUseCase) {
        this.useCase = useCase;
        this.grupoUseCase = grupoUseCase;
    }

    @GET
    @Path("/area")
    public Response find(){
        return Response
                .ok(useCase.list())
                .build();
    }

    @POST
    @Path("/area")
    public Response createArea(Area domain){
        useCase.create(domain);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/area")
    public Response modifyArea(Area domain){
        useCase.modify(domain);
        return Response
                .noContent()
                .build();
    }

    @GET
    @Path("/grupo")
    public Response findAll() {
        return Response
                .ok(grupoUseCase.findALl())
                .build();
    }

    @GET
    @Path("/grupo/area/{id}")
    public Response findGrupos(@PathParam("id") Long areaId){
        return Response
                .ok(grupoUseCase.findByArea(areaId))
                .build();
    }

    @POST
    @Path("/grupo")
    public Response create(Grupo domain) {
        grupoUseCase.create(domain);
        return Response
                .noContent()
                .build();
    }

    @PUT
    @Path("/grupo")
    public Response modify(Grupo domain) {
        grupoUseCase.modify(domain);
        return Response
                .noContent()
                .build();
    }
}
