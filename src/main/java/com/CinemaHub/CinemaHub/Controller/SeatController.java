package com.CinemaHub.CinemaHub.Controller;

import com.CinemaHub.CinemaHub.DTO.ReservationRequest;
import com.CinemaHub.CinemaHub.Entity.Seat;
import com.CinemaHub.CinemaHub.Services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/seats")
@RestController
public class SeatController {

    @Autowired
    private SeatService seatService;


//    @GetMapping("/unreserved/{showId}")
//    public List<Seat> getUnReservedSeats(@PathVariable Long showId) {
//        return seatService.getUnReservedSeatsForShow(showId);
//    }

    @GetMapping("/unreserved/{showId}")
    public  ResponseEntity <List<Seat>>getUnReservedSeats(@PathVariable Long showId) {
        List<Seat> s = seatService.getUnReservedSeatsForShow(showId);
        // HOUSEFULL
        if (s.isEmpty() || s.size()==0 ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
    }

    @PutMapping("/reserve")
    public void reserveSeats(@RequestBody List<Integer> seatIds) {
        seatService.reserveSeats(seatIds);
    }

    @PutMapping("/reserve2")
    public void reserveSeats2(@RequestBody ReservationRequest request) {
        seatService.reserveSeats2(request.getSeatNos(), request.getShowIds());
    }







}
