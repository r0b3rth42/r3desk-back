package org.r3desk.tickets.infrastructure.adapter.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.User;
import org.r3desk.tickets.domain.port.out.UserRepositoryPort;
import org.r3desk.tickets.infrastructure.adapter.out.entity.UserEntity;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity>, UserRepositoryPort {

    @Override
    public List<User> findReviewers(){


        return find(
                "role",
                "REVISOR"
        )
                .list()
                .stream()
                .map(entity -> {

                    User user = new User();

                    user.setId(entity.getId());
                    user.setNombre(entity.getNombre());
                    user.setRole(entity.getRole());
                    user.setEmail(entity.getEmail());
                    user.setCognitoId(entity.getCognitoId());

                    return user;

                })
                .toList();


    }


    @Override
    public User findByCognitoId(String cognitoId){


        UserEntity entity =
                find(
                        "cognitoId",
                        cognitoId
                )
                        .firstResult();


        if(entity==null)
            return null;



        User user = new User();

        user.setId(entity.getId());
        user.setCognitoId(entity.getCognitoId());
        user.setNombre(entity.getNombre());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());


        return user;

    }




    @Override
    public User save(User user){


        UserEntity entity = new UserEntity();


        entity.setCognitoId(
                user.getCognitoId()
        );

        entity.setEmail(
                user.getEmail()
        );


        entity.setNombre(
                user.getNombre()
        );


        entity.setRole(
                user.getRole()
        );


        persist(entity);



        user.setId(entity.getId());


        return user;

    }

}
