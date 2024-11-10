package com.CinemaHub.CinemaHub.Services;

import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    // Create
    public void saveEntry(Movie movie){
        movieRepo.save(movie);
    }

    // Read
    public List<Movie> getAll(){
        return movieRepo.findAll();
    }

    // Read By id
    public Optional<Movie> findById(Integer id){
        return movieRepo.findById(id);
    }

    public Optional<Movie> findByTitle(String title){
        return movieRepo.findByTitle(title);
    }
    // Update By id

    // Delete By id
    public void deleteById(Integer id){
        movieRepo.deleteById(id);
    }


    public List<Movie> findByGenre(String genre) {
        return movieRepo.findByGenre(genre);
    }

    public List<Movie> findByLanguage(String language) {
        return movieRepo.findByLanguage(language);
    }
}
