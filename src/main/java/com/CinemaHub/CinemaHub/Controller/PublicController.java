package com.CinemaHub.CinemaHub.Controller;

import com.CinemaHub.CinemaHub.Entity.User;
import com.CinemaHub.CinemaHub.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @GetMapping("/check")
    public String check(){
        return "Check successful";
    }


    // public
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
