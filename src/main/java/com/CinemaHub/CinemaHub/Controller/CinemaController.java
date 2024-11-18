package com.CinemaHub.CinemaHub.Controller;
/*
import com.CinemaHub.CinemaHub.Entity.Cinema;
import com.CinemaHub.CinemaHub.Services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Cinema> all = cinemaService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);

    }


    // finding by Id
    @GetMapping("/cinemaId/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable Long id) {
        Optional<Cinema> f = cinemaService.findById(id);

        if (f.isPresent()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }

    // finding by title
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getEntryByTitle(@PathVariable String name) {
        Optional<Cinema> f = cinemaService.findByname(name);
        if (f.isPresent()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }

    // find by Genre
    @GetMapping("/location/{location}")
    public ResponseEntity<?> getEntryGenre(@PathVariable String location) {
        List<Cinema> f = cinemaService.findByLocation(location);
        if (!f.isEmpty()) {
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        return new ResponseEntity<>(f, HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody Cinema cinema) {

        try {
            cinemaService.saveEntry(cinema);
            return new ResponseEntity<>(cinema, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("cinemaId/{id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long id) {
        cinemaService.deleteById(id);
        return new ResponseEntity<>(cinemaService, HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Cinema newEntry) {
        Cinema old = cinemaService.findById(id).orElse(null);
        if (old != null) {
            old.setName(newEntry.getName() != null && !newEntry.getName().equals("") ? newEntry.getName() : old.getName());
            old.setLocation(newEntry.getLocation() != null && !newEntry.getLocation().equals("") ? newEntry.getLocation() : old.getLocation());
            old.setCinemaHalls(newEntry.getCinemaHalls() != null && !newEntry.getCinemaHalls().equals("") ? newEntry.getCinemaHalls() : old.getCinemaHalls());
            cinemaService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
*/

import com.CinemaHub.CinemaHub.Entity.Cinema;
import com.CinemaHub.CinemaHub.Services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    // Create or Update Cinema
    @PostMapping
    public ResponseEntity<Cinema> createOrUpdateCinema(@RequestBody Cinema cinema) {
        Cinema savedCinema = cinemaService.saveCinema(cinema);
        return new ResponseEntity<>(savedCinema, HttpStatus.CREATED);
    }

    // Get all Cinemas
    @GetMapping
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    // Get Cinema by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable Long id) {
        Optional<Cinema> cinema = cinemaService.getCinemaById(id);
        return cinema.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Cinema by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCinema(@PathVariable Long id) {
//        cinemaService.deleteCinema(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long id) {
        cinemaService.deleteCinema(id);
        return new ResponseEntity<>(cinemaService, HttpStatus.NO_CONTENT);
    }
}
