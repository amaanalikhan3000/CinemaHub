package com.CinemaHub.CinemaHub.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Booking> bookings;

    public User(Long userId) {
        this.id = userId;
    }
}