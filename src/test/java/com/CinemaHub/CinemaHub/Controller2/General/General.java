package com.CinemaHub.CinemaHub.Controller2.General;

import com.CinemaHub.CinemaHub.Entity.Cinema;
import com.CinemaHub.CinemaHub.Entity.CinemaHall;
import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Services.CinemaHallService;
import com.CinemaHub.CinemaHub.Services.CinemaService;
import com.CinemaHub.CinemaHub.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/general")
public class General {


    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaHallService cinemaHallService;


    @GetMapping("/getAll")
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


    // Get all Cinemas
    @GetMapping("/AllCinemas")
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    // Get Cinema by ID
    @GetMapping("cinemaId/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable Long id) {
        Optional<Cinema> cinema = cinemaService.getCinemaById(id);
        return cinema.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/getAllCinema")
    public ResponseEntity<?> getAllCinemas2() {
        List<CinemaHall> all = cinemaHallService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);

    }


}
