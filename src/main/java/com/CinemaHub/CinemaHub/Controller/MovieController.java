package com.CinemaHub.CinemaHub.Controller;


import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Movie> all = movieService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);

    }


    // finding by Id
    @GetMapping("/movieId/{movieId}")
    public ResponseEntity<?> getEntryById(@PathVariable Integer movieId) {

 /*    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();  */
        Optional<Movie> f = movieService.findById(movieId);

        if (f.isPresent()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }

    // finding by title
    @GetMapping("/title/{title}")
    public ResponseEntity<?> getEntryByTitle(@PathVariable String title) {
        Optional<Movie> f = movieService.findByTitle(title);
        if (f.isPresent()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }

    // find by Genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> getEntryGenre(@PathVariable String genre) {
        List<Movie> f = movieService.findByGenre(genre);
        if (!f.isEmpty()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }

    // find by language
    @GetMapping("/language/{language}")
    public ResponseEntity<?> getEntryBylanguage(@PathVariable String language) {
        List<Movie> f = movieService.findByLanguage(language);
        if (!f.isEmpty()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }


    @PostMapping
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
    public ResponseEntity<?> updateById(@PathVariable Integer movieId,@RequestBody Movie newEntry) {
        Movie old = movieService.findById(movieId).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setGenre(newEntry.getGenre() != null && !newEntry.getGenre().equals("") ? newEntry.getGenre() : old.getGenre());
            old.setDescription(newEntry.getDescription()!=null && !newEntry.getDescription().equals("")? newEntry.getDescription(): old.getDescription());
            Optional<Integer> optionalInt = Optional.of(newEntry.getDurationInMin());

            old.setLanguage(newEntry.getLanguage()!=null && !newEntry.getLanguage().equals("")? newEntry.getLanguage(): old.getLanguage());

            old.setDurationInMin(newEntry.getDurationInMin() > 0 ? newEntry.getDurationInMin() : old.getDurationInMin());


            old.setReleaseDate(newEntry.getReleaseDate()!=null ? newEntry.getReleaseDate(): old.getReleaseDate());
            old.setCountry(newEntry.getCountry()!=null && !newEntry.getCountry().equals("")? newEntry.getCountry(): old.getCountry());

            movieService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
