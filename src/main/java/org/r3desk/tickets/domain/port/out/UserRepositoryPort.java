package org.r3desk.tickets.domain.port.out;

import org.r3desk.tickets.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {
    List<User> findReviewers();

    User findByCognitoId(String cognitoId);


    User save(User user);
}
