package com.CinemaHub.CinemaHub.Repository;


import com.CinemaHub.CinemaHub.Entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepo extends JpaRepository<MovieShow,Long> {


    @Query(value = "SELECT " +
            "    m.title AS movie_title, " +
            "    m.description AS movie_description, " +
            "    m.duration_in_min AS movie_duration, " +
            "    m.language AS movie_language, " +
            "    m.release_date AS movie_release_date, " +
            "    s.show_id, " +
            "    s.start_time, " +
            "    s.end_time, " +
            "    c.name AS cinema_name, " +
            "    c.location AS cinema_location, " +
            "    ch.title AS cinema_hall_title, " +
            "    ch.description AS cinema_hall_description " +
            "FROM " +
            "    movie m " +
            "JOIN " +
            "    movieshow s ON m.movie_id = s.movie_id " +
            "JOIN " +
            "    cinema c ON c.cinema_id = s.hall_id " +
            "JOIN " +
            "    cinemaHall ch ON s.hall_id = ch.hall_id " +
            "WHERE " +
            "    m.title = :title " +
            "ORDER BY " +
            "    s.start_time", nativeQuery = true)
    List<Object[]> findMovieShowDetailsByTitle(@Param("title") String title);


    //Optional<MovieShow> findById(Integer showId);

    Optional<MovieShow> findByShowId(Integer showId);
}
