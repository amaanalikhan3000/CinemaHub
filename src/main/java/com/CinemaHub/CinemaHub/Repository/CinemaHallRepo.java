package com.CinemaHub.CinemaHub.Repository;


import com.CinemaHub.CinemaHub.Entity.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepo extends JpaRepository<CinemaHall,Integer> {
}
