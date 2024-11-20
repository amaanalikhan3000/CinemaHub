package com.CinemaHub.CinemaHub.Controller2.User;

import com.CinemaHub.CinemaHub.DTO.MovieShowDetailsDTO;
import com.CinemaHub.CinemaHub.DTO.ReservationRequest;
import com.CinemaHub.CinemaHub.Entity.MovieShow;
import com.CinemaHub.CinemaHub.Entity.Seat;
import com.CinemaHub.CinemaHub.Repository.UserRepo;
import com.CinemaHub.CinemaHub.Services.BookingService;
import com.CinemaHub.CinemaHub.Services.SeatService;
import com.CinemaHub.CinemaHub.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class User {


    @Autowired
    private UserService userService;


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private BookingService bookingService;


    @Autowired
    private com.CinemaHub.CinemaHub.Services.showService showService;


    @Autowired
    private SeatService seatService;



    @GetMapping("/unreserved/{showId}")
    public  ResponseEntity <List<Seat>>getUnReservedSeats(@PathVariable Integer  showId) {

        List<Seat> s = seatService.getUnReservedSeatsForShow(showId);
        // HOUSEFULL
        if (s.isEmpty() || s.size()==0 ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
    }



    @PostMapping("/reserve")
    public ResponseEntity<?> reserveSeats(@RequestBody ReservationRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();


        com.CinemaHub.CinemaHub.Entity.User user = userService.findByUserName(UserName);


        boolean equals = user.getId().equals(request.getUserId().longValue());


        if (equals) {
            String message = bookingService.bookSeats(request.getUserId(), request.getShowId(), request.getSeatNos());
            HttpStatus status = message.contains("Failed") ? HttpStatus.CONFLICT : HttpStatus.OK;
            return new ResponseEntity<>(message, status);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }


    @GetMapping("/movies/{movieId}/shows")
    public ResponseEntity<Optional<MovieShow>> getShowsByMovieId(@PathVariable Integer movieId) {
        Optional<MovieShow> shows = showService.getShowsByMovieId(movieId);
        return ResponseEntity.ok(shows);
    }


    @GetMapping("/show-details")
    public List<MovieShowDetailsDTO> getMovieShowDetails(@RequestParam String title) {
        return showService.getMovieShowDetails(title);
    }

    // Authenticated
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody com.CinemaHub.CinemaHub.Entity.User newUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();
        com.CinemaHub.CinemaHub.Entity.User userIndb = userService.findByUserName(UserName);

        if(userIndb!=null){
            userIndb.setEmail(newUser.getEmail()!=null && !newUser.getEmail().equals("")? newUser.getEmail() : userIndb.getEmail());
            userIndb.setUsername(newUser.getUsername()!=null && !newUser.getUsername().equals("")? newUser.getUsername() : userIndb.getUsername());
            userIndb.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals("") ? newUser.getPassword() : userIndb.getPassword());
            userIndb.setPhoneNumber(newUser.getPhoneNumber() != null && !newUser.getPhoneNumber().equals("") ? newUser.getPhoneNumber() : userIndb.getPhoneNumber());
            userIndb.setBookings(newUser.getBookings() != null && !newUser.getBookings().isEmpty() ? newUser.getBookings() : userIndb.getBookings());

            if(userIndb.getRoles().equals("USER")){
                userService.saveNewUser(userIndb);
            }else{
                userService.saveNewAdminUser(userIndb);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestBody com.CinemaHub.CinemaHub.Entity.User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();
        com.CinemaHub.CinemaHub.Entity.User user2 = userService.findByUserName(UserName);
        boolean equals = user2.getId().equals(user.getId());
        if (equals) {
            userRepo.deleteById(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
