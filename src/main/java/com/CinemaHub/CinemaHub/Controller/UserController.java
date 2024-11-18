package com.CinemaHub.CinemaHub.Controller;


import com.CinemaHub.CinemaHub.Entity.Movie;
import com.CinemaHub.CinemaHub.Entity.User;
import com.CinemaHub.CinemaHub.Repository.UserRepo;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    //MAKING THIS IN ADMIN
    @GetMapping
    public ResponseEntity<?> getAllUser() {
       // return userService.getAll();


        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
            //return new ResponseEntity<>("OK", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Error message", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();

        User user = userService.findByUserName(UserName);

        boolean equals = user.getId().equals(id);


        if (equals) {
            Optional<User> f = userService.findById(id);

            if (f.isPresent()) {
                return new ResponseEntity<>(f, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Authenticated
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String UserName = authentication.getName();
        User userIndb = userService.findByUserName(UserName);

        if(userIndb!=null){
            userIndb.setEmail(newUser.getEmail()!=null && !newUser.getEmail().equals("")? newUser.getEmail() : userIndb.getEmail());
            userIndb.setUsername(newUser.getUsername()!=null && !newUser.getUsername().equals("")? newUser.getUsername() : userIndb.getUsername());
            userIndb.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals("") ? newUser.getPassword() : userIndb.getPassword());
            userIndb.setPhoneNumber(newUser.getPhoneNumber() != null && !newUser.getPhoneNumber().equals("") ? newUser.getPhoneNumber() : userIndb.getPhoneNumber());
            userIndb.setBookings(newUser.getBookings() != null && !newUser.getBookings().isEmpty() ? newUser.getBookings() : userIndb.getBookings());
            userService.saveNewUser(userIndb);
        return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }








    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String UserName = authentication.getName();

        User user2 = userService.findByUserName(UserName);

        boolean equals = user2.getId().equals(user.getId());

        if (equals) {
            userRepo.deleteById(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }


}
