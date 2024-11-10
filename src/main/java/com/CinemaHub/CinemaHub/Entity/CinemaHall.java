package com.CinemaHub.CinemaHub.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cinemahall")
@NoArgsConstructor
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hallId;

    @ManyToOne
    @JoinColumn(name = "cinema_id") // Link to Cinema (cinema_id)
    private Cinema cinema;

    private String title;
    private String description;

}
