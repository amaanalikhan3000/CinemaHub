package com.CinemaHub.CinemaHub.Controller2.Admin.movie;

import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class createEntry {

    @Autowired
    private MovieService movieService;

    @PostMapping("/movie")
    public ResponseEntity<?> createEntry(@RequestBody Movie movie) {

        try {
            if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
                return new ResponseEntity<>("Title is required", HttpStatus.BAD_REQUEST);
            }
            if (movie.getLanguage() == null || movie.getLanguage().isEmpty()) {
                return new ResponseEntity<>("Language is required", HttpStatus.BAD_REQUEST);
            }
            if (movie.getDurationInMin() <= 0) {
                return new ResponseEntity<>("Duration must be greater than 0", HttpStatus.BAD_REQUEST);
            }
            if (movie.getReleaseDate() == null) {
                return new ResponseEntity<>("Release date is required", HttpStatus.BAD_REQUEST);
            }
            if (movie.getDescription() == null || movie.getDescription().isEmpty()) {
                return new ResponseEntity<>("Description is required", HttpStatus.BAD_REQUEST);
            }
            if (movie.getCountry() == null || movie.getCountry().isEmpty()) {
                movie.setCountry("Unknown"); // Default value for country
            }
            if (movie.getGenre() == null || movie.getGenre().isEmpty()) {
                movie.setGenre("Not Specified"); // Default value for genre
            }
            movieService.saveEntry(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while creating the movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
