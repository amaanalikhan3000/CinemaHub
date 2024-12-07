package com.CinemaHub.CinemaHub.Controller2.Admin;

import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Repository.UserRepo;
import com.CinemaHub.CinemaHub.Services.CinemaHallService;
import com.CinemaHub.CinemaHub.Services.CinemaService;
import com.CinemaHub.CinemaHub.Services.MovieService;
import com.CinemaHub.CinemaHub.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    @PostMapping("/movie")
    public ResponseEntity<?> createEntry(@RequestBody Movie movie) {

        try {
            movieService.saveEntry(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }


}
