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
import java.util.*;


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
        try {
            if (movieService.findById(movieId).isEmpty()) {
                return new ResponseEntity<>("Movie Not found", HttpStatus.NOT_FOUND);
            } else {
                movieService.deleteById(movieId);
                return new ResponseEntity<>("Movie Deleted Successfully", HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("movieId/{movieId}")
    public ResponseEntity<?> updateById(@PathVariable Integer movieId, @RequestBody Movie newEntry) {
        try {
            Movie old = movieService.findById(movieId).orElse(null);
            if (old == null) {
                return new ResponseEntity<>("Movie Not Found", HttpStatus.NOT_FOUND);
            }

            // Initialize a StringBuilder to collect error messages
            StringBuilder errorMessage = new StringBuilder("Invalid fields: ");
            boolean isInvalid = false;

            // Check and update genre if provided
            if (newEntry.getGenre() != null && !newEntry.getGenre().isBlank()) {
                old.setGenre(newEntry.getGenre());
            } else if (newEntry.getGenre() != null && newEntry.getGenre().isBlank()) {
                errorMessage.append("Genre, ");
                isInvalid = true;
            }

            // Check and update description if provided
            if (newEntry.getDescription() != null && !newEntry.getDescription().isBlank()) {
                old.setDescription(newEntry.getDescription());
            } else if (newEntry.getDescription() != null && newEntry.getDescription().isBlank()) {
                errorMessage.append("Description, ");
                isInvalid = true;
            }


            // Check and update title if provided
            if (newEntry.getTitle() != null && !newEntry.getTitle().isBlank()) {
                old.setTitle(newEntry.getTitle());
            } else if (newEntry.getTitle() != null && newEntry.getTitle().isBlank()) {
                // This ensures we don't mistakenly treat empty strings as invalid.
                // So, only append "Title" if it's explicitly passed but empty.
                errorMessage.append("Title, ");
                isInvalid = true;
            }

            // Check and update language if provided
            if (newEntry.getLanguage() != null && !newEntry.getLanguage().isBlank()) {
                old.setLanguage(newEntry.getLanguage());
            } else if (newEntry.getLanguage() != null && newEntry.getLanguage().isBlank()) {
                errorMessage.append("Language, ");
                isInvalid = true;
            }

            // Check and update duration if provided
            if (newEntry.getDurationInMin() > 0) {
                old.setDurationInMin(newEntry.getDurationInMin());
            } else if (newEntry.getDurationInMin() > 0) {
                errorMessage.append("DurationInMin, ");
                isInvalid = true;
            }

            // Check and update release date if provided
            if (newEntry.getReleaseDate() != null) {
                old.setReleaseDate(newEntry.getReleaseDate());
            } else if (newEntry.getReleaseDate() != null) {
                errorMessage.append("ReleaseDate, ");
                isInvalid = true;
            }

            // If any invalid field was found, return the error message
            if (isInvalid) {
                // Remove the last comma and space
                errorMessage.setLength(errorMessage.length() - 2);
                return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
            }

            // Save the updated movie entry
            movieService.saveEntry(old);

            return new ResponseEntity<>(old, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/cinema")
    public ResponseEntity<?> createOrUpdateCinema(@RequestBody Cinema cinema) {
        try {
            StringBuilder errorMessage = new StringBuilder("Invalid fields: ");
            boolean isInvalid = false;

            if (cinema.getName() == null || cinema.getName().isBlank()) {
                errorMessage.append("Name, ");
                isInvalid = true;
            }

            if (cinema.getLocation() == null || cinema.getLocation().isBlank()) {
                errorMessage.append("Location, ");
                isInvalid = true;
            }

            Integer TotalCinemaHalls = cinema.getTotalCinemaHalls();
            if (TotalCinemaHalls == null || TotalCinemaHalls <= 0) {
                errorMessage.append("TotalCinemaHalls, ");
                isInvalid = true;
            }

            if (isInvalid) {
                // Remove the last comma and space
                errorMessage.setLength(errorMessage.length() - 2);
                return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
            }


            Cinema savedCinema = cinemaService.saveCinema(cinema);
            return new ResponseEntity<>(savedCinema, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/cinemaId/{cinemaId}")
    public ResponseEntity<?> DeleteById2(@PathVariable Integer cinemaId) {
        try {
            if (cinemaService.findById(cinemaId).isEmpty()) {
                return new ResponseEntity<>("Cinema Not found", HttpStatus.NOT_FOUND);
            } else {
                cinemaService.deleteById(cinemaId);
                return new ResponseEntity<>("Cinema Deleted Successfully", HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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

        // Iterate through validation errors and map field-specific messages
        for (FieldError error : result.getFieldErrors()) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }

        return errorMessages;
    }


}
