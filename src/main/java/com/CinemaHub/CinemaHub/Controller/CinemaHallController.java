package com.CinemaHub.CinemaHub.Controller;



import com.CinemaHub.CinemaHub.Entity.CinemaHall;
import com.CinemaHub.CinemaHub.Services.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CinemaHall")
public class CinemaHallController {

    @Autowired
    private CinemaHallService cinemaHallService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CinemaHall> all = cinemaHallService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);

    }

//        @GetMapping
//    public HttpEntity<List<CinemaHall>> getAllCinemas() {
//        List<CinemaHall> cinemas = cinemaHallService.getAll();
//        return new ResponseEntity<>(cinemas, HttpStatus.OK);
//    }

        @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody CinemaHall CinemaHall) {

        try {
            cinemaHallService.saveEntry(CinemaHall);
            return new ResponseEntity<>(CinemaHall, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

}
