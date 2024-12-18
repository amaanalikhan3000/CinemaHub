package com.CinemaHub.CinemaHub.Repository;


import com.CinemaHub.CinemaHub.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat,Long> {

    @Query("SELECT s FROM Seat s WHERE s.movieShow.showId = :showId AND s.isReserved = FALSE")
    List<Seat> findNonReservedSeatsByShowId(@Param("showId") Integer  showId);

    @Modifying
    @Transactional
    @Query("UPDATE Seat s SET s.isReserved = TRUE WHERE s.seatId IN :seatIds")
    void updateSeatReservationStatus(List<Integer> seatIds);

    @Modifying
    @Transactional
    @Query("UPDATE Seat s SET s.isReserved = TRUE WHERE s.seatNo IN :seatNos AND s.movieShow.showId IN :showIds")
    void updateSeatReservationStatus2(List<Integer> seatNos, List<Integer> showIds);


    List<Seat> findBySeatNoInAndMovieShow_ShowId(List<Integer> seatNumbers, Integer showId);
}




