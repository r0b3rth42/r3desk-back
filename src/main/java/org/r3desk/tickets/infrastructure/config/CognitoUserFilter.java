package org.r3desk.tickets.infrastructure.config;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.r3desk.tickets.domain.port.in.SyncUserUseCase;

@Provider
public class CognitoUserFilter implements ContainerRequestFilter {


    @Inject
    JsonWebToken jwt;


    @Inject
    SyncUserUseCase syncUser;



    @Override
    public void filter(
            ContainerRequestContext ctx
    ){


        if(jwt.getSubject()!=null){


            syncUser.execute(
                    jwt.getSubject(),
                    jwt.getClaim("email"),
                    jwt.getClaim("name")
            );

        }

    }

}