package com.CinemaHub.CinemaHub.Controller2.OAuth2;

import com.CinemaHub.CinemaHub.Entity.User;
import com.CinemaHub.CinemaHub.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Oauth2")
public class Oauth2 {


    @Autowired
    private UserService userService;
    // otp
    @PostMapping("/createAdmin")
    public void createAdminUser(@RequestBody User user) {
        userService.saveNewAdminUser(user);
    }
    // otp
    @PostMapping("/createUser")
    public void createUserUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
