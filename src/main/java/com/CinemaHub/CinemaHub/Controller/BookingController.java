package com.CinemaHub.CinemaHub.Controller;

import com.CinemaHub.CinemaHub.DTO.ReservationRequest;
import com.CinemaHub.CinemaHub.Entity.User;
import com.CinemaHub.CinemaHub.Services.BookingService;
import com.CinemaHub.CinemaHub.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveSeats(@RequestBody ReservationRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();


        User user = userService.findByUserName(UserName);


        boolean equals = user.getId().equals(request.getUserId().longValue());


        if (equals) {
            String message = bookingService.bookSeats(request.getUserId(), request.getShowId(), request.getSeatNos());
            HttpStatus status = message.contains("Failed") ? HttpStatus.CONFLICT : HttpStatus.OK;
            return new ResponseEntity<>(message, status);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }


}
