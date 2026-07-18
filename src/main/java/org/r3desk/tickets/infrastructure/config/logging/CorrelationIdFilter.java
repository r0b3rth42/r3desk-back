package org.r3desk.tickets.infrastructure.config.logging;

import io.quarkus.logging.Log;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.MDC;

import java.io.IOException;
import java.util.UUID;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class CorrelationIdFilter implements ContainerRequestFilter, ContainerResponseFilter {


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String correlationId = requestContext.getHeaderString(Constants.HEADER_NAME);

        // Si el cliente no envía uno, se genera automáticamente
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        // Disponible para otros filtros
        requestContext.setProperty(Constants.PROPERTY_NAME, correlationId);

        // Disponible para todos los LOG.info()
        MDC.put("correlationId", correlationId);

        Log.debugf("CorrelationId: %s", correlationId);
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        String correlationId =
                (String) requestContext.getProperty(Constants.PROPERTY_NAME);

        if (correlationId != null) {
            responseContext.getHeaders().add(Constants.HEADER_NAME, correlationId);
        }

        // Muy importante limpiar el MDC
        MDC.remove("correlationId");
    }
}