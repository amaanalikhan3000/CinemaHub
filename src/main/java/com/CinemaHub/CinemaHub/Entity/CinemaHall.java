package com.CinemaHub.CinemaHub.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Lob // Maps to 'TEXT' in most databases
    @Column(columnDefinition = "TEXT") // Explicitly specify the column type
    private String description;

}
