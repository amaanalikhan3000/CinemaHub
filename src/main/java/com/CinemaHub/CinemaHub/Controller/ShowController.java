package com.CinemaHub.CinemaHub.Controller;



import com.CinemaHub.CinemaHub.DTO.MovieShowDetailsDTO;
import com.CinemaHub.CinemaHub.Entity.MovieShow;
import com.CinemaHub.CinemaHub.Services.showService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ShowController {


    @Autowired
    private showService showService;


    @GetMapping("/movies/{movieId}/shows")
    public ResponseEntity<Optional<MovieShow>> getShowsByMovieId(@PathVariable Long movieId) {
        Optional<MovieShow> shows = showService.getShowsByMovieId(movieId);
        return ResponseEntity.ok(shows);
    }


    @GetMapping("/show-details")
    public List<MovieShowDetailsDTO> getMovieShowDetails(@RequestParam String title) {
        return showService.getMovieShowDetails(title);
    }

}
