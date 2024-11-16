package com.CinemaHub.CinemaHub.Controller;


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
    public List<User> getAllUser(){
        return userService.getAll();
    }





    // Authenticated
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String UserName = authentication.getName();
        Optional<User> userIndb = userService.findByUserName(UserName);

        userIndb.get().setUsername(user.getUsername());
        userIndb.get().setPassword(user.getPassword());
        userIndb.get().setEmail(user.getEmail());
        userIndb.get().setPhoneNumber(user.getPhoneNumber());
        userIndb.get().setBookings(user.getBookings());
        userService.saveEntry(userIndb.orElse(null));
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userRepo.deleteByusername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
