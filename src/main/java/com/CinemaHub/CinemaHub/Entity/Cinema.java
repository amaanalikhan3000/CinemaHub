package com.CinemaHub.CinemaHub.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cinemaId;

    private String name;
    private int totalCinemaHalls;
    private String location;

    // Getters and setters
}