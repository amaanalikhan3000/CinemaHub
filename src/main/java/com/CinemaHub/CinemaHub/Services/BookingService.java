package com.CinemaHub.CinemaHub.Services;

import com.CinemaHub.CinemaHub.Entity.Booking;
import com.CinemaHub.CinemaHub.Entity.MovieShow;
import com.CinemaHub.CinemaHub.Entity.Seat;
import com.CinemaHub.CinemaHub.Entity.User;
import com.CinemaHub.CinemaHub.Repository.BookingRepo;
import com.CinemaHub.CinemaHub.Repository.SeatRepo;
import com.CinemaHub.CinemaHub.Repository.ShowRepo;
import com.CinemaHub.CinemaHub.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private SeatRepo seatRepository;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private ShowRepo showRepo;



//    @Transactional
    public String bookSeats(Integer userId, Integer showId, List<Integer> seatNumbers) {

        List<Seat> seats = seatRepository.findBySeatNoInAndMovieShow_ShowId(seatNumbers, showId);

        Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));

        Optional<MovieShow> movieShowOpt = showRepo.findByShowId(showId);

        if (userOpt.isEmpty() || movieShowOpt.isEmpty()) {
            return "Reservation Status - Failed: Invalid user ID or show ID.";
        }

        if (seats.size() != seatNumbers.size()) {
            return "Reservation Status - Failed: Some seats are unavailable or do not exist.";
        }

        if (seats.size() != seatNumbers.size()) {
            return "Reservation Status - Failed: Some seats are unavailable or do not exist.";
        }

        // Ensure no seat is reserved
        for (Seat seat : seats) {
            if (seat.isReserved()) {
                return "Reservation Status - Failed: Some seats are already reserved.";
            }
        }

        try {
            // Reserve each seat and create a new booking
            seats.forEach(seat -> seat.setReserved(true));
            seatRepository.saveAll(seats);

            Booking booking = new Booking();
            booking.setUser(userOpt.get());               // Using the existing user from the database
            booking.setMovieShow(movieShowOpt.get());      // Using the existing movie show from the database
            booking.setSeats(seats);
            bookingRepo.save(booking);

            return "Booking successful.";
        } catch (OptimisticLockException e) {
            return "Reservation Status - Failed: Some seats were reserved by another user.";
        }
    }


    //

}
