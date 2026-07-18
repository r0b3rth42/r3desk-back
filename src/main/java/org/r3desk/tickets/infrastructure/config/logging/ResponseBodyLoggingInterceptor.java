package org.r3desk.tickets.infrastructure.config.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;

import java.io.IOException;

@Provider
@Priority(Priorities.USER)
public class ResponseBodyLoggingInterceptor implements WriterInterceptor {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {

        Object entity = context.getEntity();

        if (entity != null) {

            try {

                String json = objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(entity);

                Log.infof("""
                        ==================== RESPONSE BODY ====================

                        %s

                        =======================================================
                        """, json);

            } catch (Exception e) {

                Log.warn("No fue posible serializar la respuesta para logging.", e);

            }

        }

        context.proceed();
    }

}