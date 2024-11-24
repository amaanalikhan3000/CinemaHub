package com.CinemaHub.CinemaHub.Controller2.Admin;

import com.CinemaHub.CinemaHub.Entity.Cinema;
import com.CinemaHub.CinemaHub.Entity.CinemaHall;
import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Entity.User;
import com.CinemaHub.CinemaHub.Repository.UserRepo;
import com.CinemaHub.CinemaHub.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class Admin {

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

    @DeleteMapping("movieId/{movieId}")
    public ResponseEntity<?> DeleteById(@PathVariable Integer movieId) {
        movieService.deleteById(movieId);
        return new ResponseEntity<>(movieService, HttpStatus.NO_CONTENT);
    }

    @PutMapping("movieId/{movieId}")
    public ResponseEntity<?> updateById(@PathVariable Integer movieId, @RequestBody Movie newEntry) {
        Movie old = movieService.findById(movieId).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setGenre(newEntry.getGenre() != null && !newEntry.getGenre().equals("") ? newEntry.getGenre() : old.getGenre());
            old.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().equals("") ? newEntry.getDescription() : old.getDescription());
            Optional<Integer> optionalInt = Optional.of(newEntry.getDurationInMin());
            old.setLanguage(newEntry.getLanguage() != null && !newEntry.getLanguage().equals("") ? newEntry.getLanguage() : old.getLanguage());
            old.setDurationInMin(newEntry.getDurationInMin() > 0 ? newEntry.getDurationInMin() : old.getDurationInMin());
            old.setReleaseDate(newEntry.getReleaseDate() != null ? newEntry.getReleaseDate() : old.getReleaseDate());
            old.setCountry(newEntry.getCountry() != null && !newEntry.getCountry().equals("") ? newEntry.getCountry() : old.getCountry());

            movieService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/cinema")
    public ResponseEntity<Cinema> createOrUpdateCinema(@RequestBody Cinema cinema) {
        Cinema savedCinema = cinemaService.saveCinema(cinema);
        return new ResponseEntity<>(savedCinema, HttpStatus.CREATED);
    }


    @DeleteMapping("/cinemaId/{cinemaId}")
    public ResponseEntity<?> DeleteById2(@PathVariable Integer cinemaId) {
        cinemaService.deleteById(cinemaId);
        return new ResponseEntity<>(cinemaService, HttpStatus.NO_CONTENT);
    }


    @PostMapping("/cinemaHall")
    public ResponseEntity<?> createEntry(@RequestBody CinemaHall CinemaHall) {

        try {
            cinemaHallService.saveEntry(CinemaHall);
            return new ResponseEntity<>(CinemaHall, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser() {
        // return userService.getAll();

        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
            //return new ResponseEntity<>("OK", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Error message", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // OTP
    @PostMapping("/create-user")
    public void createAdminUser(@RequestBody User user) {
        userService.saveNewAdminUser(user);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();

        User user = userService.findByUserName(UserName);

        boolean equals = user.getId().equals(id);


        if (equals) {
            Optional<User> f = userService.findById(id);

            if (f.isPresent()) {
                return new ResponseEntity<>(f, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
