package org.r3desk.tickets.infrastructure.config.logging;

import io.quarkus.logging.Log;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
@Priority(Priorities.USER)
public class RequestBodyLoggingInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context)
            throws IOException, WebApplicationException {

        if (context.getInputStream() != null) {

            byte[] body = context.getInputStream().readAllBytes();

            // Restaurar el InputStream para que Jackson pueda leerlo
            context.setInputStream(new ByteArrayInputStream(body));

            String json = new String(body, StandardCharsets.UTF_8);

            if (!json.isBlank()) {

                Log.info("""
                        ==================== REQUEST BODY ====================

                        %s

                        ======================================================
                        """.formatted(pretty(json)));

            }

        }

        return context.proceed();
    }

    private String pretty(String json) {

        return json.replace(",", ",\n")
                .replace("{", "{\n")
                .replace("}", "\n}")
                .replace("[", "[\n")
                .replace("]", "\n]");
    }

}