package org.r3desk.tickets.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.r3desk.tickets.domain.model.User;
import org.r3desk.tickets.domain.port.out.TicketRepositoryPort;
import org.r3desk.tickets.domain.port.out.UserRepositoryPort;

import java.util.Comparator;

@ApplicationScoped
public class ReviewerAssignmentService {

    private final UserRepositoryPort userRepositoryPort;
    private final TicketRepositoryPort ticketRepositoryPort;



    public ReviewerAssignmentService(
            UserRepositoryPort userRepositoryPort,
            TicketRepositoryPort ticketRepositoryPort
    ){

        this.userRepositoryPort = userRepositoryPort;
        this.ticketRepositoryPort = ticketRepositoryPort;

    }



    public User assignReviewer(){


        return userRepositoryPort.findReviewers()
                .stream()
                .min(
                        Comparator.comparing(
                                user ->
                                        ticketRepositoryPort.countAssignedTickets(user.getId())
                        )
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "No existen revisores disponibles"
                        )
                );


    }
}
