package com.CinemaHub.CinemaHub.Entity;



import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    private String title;
    private String description;
    private int durationInMin;
    private String language;
    private LocalDateTime releaseDate;
    private String country;
    private String genre;





}
