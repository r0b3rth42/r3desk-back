package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.r3desk.tickets.domain.model.User;
import org.r3desk.tickets.domain.port.in.SyncUserUseCase;
import org.r3desk.tickets.domain.port.out.UserRepositoryPort;



@ApplicationScoped
public class SyncUserService implements SyncUserUseCase {

    private final UserRepositoryPort repository;

    public SyncUserService(
            UserRepositoryPort repository
    ){
        this.repository = repository;
    }

    @Override
    @Transactional
    public User execute(String cognitoId,
                        String email,
                        String nombre) {

        User user =repository.findByCognitoId(cognitoId);

        if(user != null){
            return user;
        }

        User nuevo = new User();

        nuevo.setCognitoId(cognitoId);
        nuevo.setEmail(email);
        nuevo.setNombre(nombre);

        // regla de negocio
        nuevo.setRole("USUARIO");

        return repository.save(nuevo);

    }

}