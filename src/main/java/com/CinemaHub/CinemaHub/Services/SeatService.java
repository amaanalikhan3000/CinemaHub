package com.CinemaHub.CinemaHub.Services;


import com.CinemaHub.CinemaHub.Entity.Seat;
import com.CinemaHub.CinemaHub.Repository.SeatRepo;
import com.CinemaHub.CinemaHub.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SeatService {

    @Autowired
    private SeatRepo seatRepo;


    @Autowired
    private ShowRepo showRepo;


    public List<Seat> getUnReservedSeatsForShow(Integer  showId) {
        return seatRepo.findNonReservedSeatsByShowId(showId);  // This uses the native query with LIMIT 5
    }


    public void reserveSeats(List<Integer> seatIds) {
        seatRepo.updateSeatReservationStatus(seatIds);
    }


    public void reserveSeats2(List<Integer> seatNos, List<Integer> showIds) {
        seatRepo.updateSeatReservationStatus2(seatNos, showIds);
    }




//    @Transactional
//    public String reserveSeat(int seatNo, long showId) {
//        // Check if the show exists
//        Optional<MovieShow> movieShowOptional = showRepo.findById(showId);
//        if (movieShowOptional.isEmpty()) {
//            return "Show not found!";
//        }
//
//        MovieShow movieShow = movieShowOptional.get();
//
//        // Find the seat by seatNo and showId
//        Optional<Seat> seatOptional = seatRepo.findBySeatNoAndMovieShow(seatNo, movieShow);
//        if (seatOptional.isEmpty()) {
//            return "Seat not found for this show!";
//        }
//
//        Seat seat = seatOptional.get();
//
//        // Check if the seat is already reserved
//        if (seat.isReserved()) {
//            return "Seat is already reserved!";
//        }
//
//        // Mark the seat as reserved
//        seat.setReserved(true);
//        seatRepo.save(seat); // Optimistic Locking ensures no race condition here
//        return "Seat reserved successfully!";
//    }


}



