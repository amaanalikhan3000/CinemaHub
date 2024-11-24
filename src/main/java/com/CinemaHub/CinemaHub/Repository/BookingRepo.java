package com.CinemaHub.CinemaHub.Repository;

import com.CinemaHub.CinemaHub.Entity.Booking;
import com.CinemaHub.CinemaHub.Entity.MovieShow;
import com.CinemaHub.CinemaHub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    Booking findTopByUserAndMovieShowOrderByBookingIdDesc(User user, MovieShow movieShow);
}
