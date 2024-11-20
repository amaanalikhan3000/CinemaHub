package com.CinemaHub.CinemaHub.Services;

import com.CinemaHub.CinemaHub.Entity.Cinema;
import com.CinemaHub.CinemaHub.Exceptions.ResourceNotFoundException;
import com.CinemaHub.CinemaHub.Repository.CinemaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    /*
    @Autowired
    private CinemaRepo cinemaRepo;

    public List<Cinema> getAll() {
        return cinemaRepo.findAll();
    }

    public Optional<Cinema> findById(Long id) {
        return cinemaRepo.findById(id);
    }

    public Optional<Cinema> findByname(String name) {
        return cinemaRepo.findByName(name);
    }

    public List<Cinema> findByLocation(String location) {
        return cinemaRepo.findByLocation(location);
    }

    public void saveEntry(Cinema cinema) {
        cinemaRepo.save(cinema);
    }

    public void deleteById(Long id) {
        cinemaRepo.deleteById(id);
    }
 */

    @Autowired
    private CinemaRepo cinemaRepository;

    // Create or Update Cinema
    public Cinema saveCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    // Get all Cinemas
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    // Get Cinema by ID
    public Optional<Cinema> getCinemaById(Long id) {
        return cinemaRepository.findById(Math.toIntExact(id));
    }

    // Delete Cinema by ID
//    public void deleteCinema(Long id) {
//        cinemaRepository.deleteById(id);
//    }


    //@Transactional
//    public void deleteById(Integer cinemaId) {
//        Cinema cinema = cinemaRepository.findById(cinemaId)
//                .orElseThrow(() -> new ResourceNotFoundException("Cinema not found"));
//        cinemaRepository.deleteById(cinemaId);
//        cinemaRepository.delete(cinema);
//    }


    public void deleteById(Integer cinemaId){
        cinemaRepository.deleteById(cinemaId);
    }
}


