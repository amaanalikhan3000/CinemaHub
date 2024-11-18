package com.CinemaHub.CinemaHub.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "User")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Booking> bookings;

    public User(Long userId) {
        this.id = userId;
    }


    @Convert(converter = RolesConverter.class)
    private List<String> roles;
}