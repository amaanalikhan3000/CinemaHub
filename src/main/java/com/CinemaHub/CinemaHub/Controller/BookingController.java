package com.CinemaHub.CinemaHub.Controller;

import com.CinemaHub.CinemaHub.DTO.ReservationRequest;
import com.CinemaHub.CinemaHub.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveSeats(@RequestBody ReservationRequest request) {
        String message = bookingService.bookSeats(request.getUserId(), request.getShowId(), request.getSeatNos());
        HttpStatus status = message.contains("Failed") ? HttpStatus.CONFLICT : HttpStatus.OK;
        return new ResponseEntity<>(message, status);
    }



}
