package org.r3desk.tickets.infrastructure.config.logging;

import io.quarkus.logging.Log;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.MDC;

import java.time.LocalDateTime;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {


    @Override
    public Response toResponse(Exception exception) {

        String correlationId = getCorrelationId();


        Log.errorf(
                exception,
                """
                ==================== UNHANDLED ERROR ====================

                CorrelationId : %s
                Exception     : %s
                Message       : %s

                ========================================================
                """,
                correlationId,
                exception.getClass().getName(),
                exception.getMessage()
        );


        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(
                        Map.of(
                                "timestamp", LocalDateTime.now(),
                                "status", 500,
                                "error", "Internal Server Error",
                                "message", "Ha ocurrido un error interno",
                                "correlationId", correlationId
                        )
                )
                .build();
    }


    private String getCorrelationId(){

        Object value = MDC.get("correlationId");

        return value != null
                ? value.toString()
                : "-";
    }

}