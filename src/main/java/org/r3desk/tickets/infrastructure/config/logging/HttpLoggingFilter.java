package org.r3desk.tickets.infrastructure.config.logging;

import io.quarkus.logging.Log;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Const;

import java.io.IOException;

@Provider
@Priority(Priorities.USER)
public class HttpLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String START_TIME = "request-start-time";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        requestContext.setProperty(START_TIME, System.currentTimeMillis());

        String correlationId = (String) requestContext.getProperty(Constants.PROPERTY_NAME);

        if (correlationId == null) {
            correlationId = "-";
        }

        Log.infof("""
                ==================== HTTP REQUEST ====================
                CorrelationId : %s
                Method        : %s
                URI           : %s
                Query         : %s
                Remote Address: %s

                Headers:
                %s
                ======================================================
                """,
                correlationId,
                requestContext.getMethod(),
                requestContext.getUriInfo().getRequestUri(),
                requestContext.getUriInfo().getRequestUri().getQuery(),
                getRemoteAddress(requestContext),
                formatHeaders(requestContext));
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {


        Long start = (Long) requestContext.getProperty(START_TIME);

        long duration = 0;

        if (start != null) {
            duration = System.currentTimeMillis() - start;
        }


        String correlationId =
                (String) requestContext.getProperty(Constants.PROPERTY_NAME);


        if (correlationId == null) {
            correlationId = "-";
        }


        Log.infof("""
            ==================== HTTP RESPONSE ====================

            CorrelationId : %s
            Status        : %d
            Duration      : %d ms

            =======================================================
            """,
                correlationId,
                responseContext.getStatus(),
                duration
        );
    }

    private String formatHeaders(ContainerRequestContext context) {

        StringBuilder builder = new StringBuilder();

        context.getHeaders().forEach((key, values) -> {

            if ("authorization".equalsIgnoreCase(key)) {
                builder.append(key)
                        .append(": ")
                        .append("*****")
                        .append(System.lineSeparator());
            } else {
                builder.append(key)
                        .append(": ")
                        .append(String.join(", ", values))
                        .append(System.lineSeparator());
            }

        });

        return builder.toString();
    }

    /**
     * En AWS Lambda normalmente no existe una IP directa del cliente.
     * Este método intenta obtenerla de los headers comunes.
     */
    private String getRemoteAddress(ContainerRequestContext context) {

        String forwarded = context.getHeaderString("X-Forwarded-For");

        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }

        String realIp = context.getHeaderString("X-Real-IP");

        if (realIp != null && !realIp.isBlank()) {
            return realIp;
        }

        return "UNKNOWN";
    }
}