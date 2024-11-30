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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> createEntry(@Valid @RequestBody Movie movie) {

        try {
            if (movie.getCountry() == null || movie.getCountry().isEmpty()) {
                movie.setCountry("Unknown"); // Default value for country
            }
            if (movie.getGenre() == null || movie.getGenre().isEmpty()) {
                movie.setGenre("Not Specified"); // Default value for genre
            }
            movieService.saveEntry(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("movieId/{movieId}")
    public ResponseEntity<String> DeleteById(@PathVariable Integer movieId) {
        try{
            if(movieService.findById(movieId).isEmpty()){
                return new ResponseEntity<>("Movie Not found", HttpStatus.NOT_FOUND);
            }else{
                movieService.deleteById(movieId);
                return new ResponseEntity<>("Movie Deleted Successfully", HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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





    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMessages = new HashMap<>();


        BindingResult result = ex.getBindingResult();

        // Iterate through all the validation errors
        for (FieldError error : result.getFieldErrors()) {
            // Customize the error messages for the fields
            if ("title".equals(error.getField())) {
                errorMessages.put("title", "Title is required and cannot be blank");
            } else if ("durationInMin".equals(error.getField())) {
                errorMessages.put("durationInMin", "Duration must be at least 1 minute");
            } else if ("language".equals(error.getField())) {
                errorMessages.put("language", "Language is required and cannot be blank");
            } else if ("releaseDate".equals(error.getField())) {
                errorMessages.put("releaseDate", "Release date cannot be in the future");
            }
        }

        return errorMessages;
    }

}
