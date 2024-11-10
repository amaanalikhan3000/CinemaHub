package com.CinemaHub.CinemaHub.Services;


import com.CinemaHub.CinemaHub.Entity.CinemaHall;
import com.CinemaHub.CinemaHub.Repository.CinemaHallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaHallService {

    @Autowired
    private CinemaHallRepo cinemaHallRepo;

    public List<CinemaHall> getAll() {
        return cinemaHallRepo.findAll();
    }

    public void saveEntry(CinemaHall cinemaHall) {
        cinemaHallRepo.save(cinemaHall);
    }
}
