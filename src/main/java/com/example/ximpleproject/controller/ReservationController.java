package com.example.ximpleproject.controller;

import com.example.ximpleproject.dto.ReservationDto;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "Make the reservation of the book",
            description = "If the books is available, the reservation is confirmed, else, a message error is returned ")
    @PostMapping
    public ResponseEntity<String> makeReservation(@RequestParam(required = true)  Long bookId, @RequestParam(required = true) Long userId) throws UserNotFoundException, BookNotFoundException {

        boolean hasReservation = reservationService.makeReservation(bookId, userId);

        if(hasReservation){
            return ResponseEntity.ok("Reservation made");
        }

        return new ResponseEntity<>("Reservation not made, due to the book already being reserved by another user", HttpStatus.ACCEPTED);
    }

}
