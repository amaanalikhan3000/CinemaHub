package com.CinemaHub.CinemaHub.Repository;

import com.CinemaHub.CinemaHub.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CinemaRepo extends JpaRepository<Cinema,Long> {
  /*  Optional<Cinema> findByName(String name);
    List<Cinema> findByLocation(String location);
   */
}
