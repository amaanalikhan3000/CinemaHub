package com.CinemaHub.CinemaHub.DTO;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MovieShowDetailsDTO {
    private String movieTitle;
    private String movieDescription;
    private int movieDuration;
    private String movieLanguage;
    private LocalDateTime movieReleaseDate;
    private Long showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String cinemaName;
    private String cinemaLocation;
    private String cinemaHallTitle;
    private String cinemaHallDescription;

    // Parameterized constructor to match the fields in the query
    public MovieShowDetailsDTO(String movieTitle, String movieDescription, int movieDuration,
                               String movieLanguage, LocalDateTime movieReleaseDate,
                               Long showId, LocalDateTime startTime, LocalDateTime endTime,
                               String cinemaName, String cinemaLocation,
                               String cinemaHallTitle, String cinemaHallDescription) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDuration = movieDuration;
        this.movieLanguage = movieLanguage;
        this.movieReleaseDate = movieReleaseDate;
        this.showId = showId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaName = cinemaName;
        this.cinemaLocation = cinemaLocation;
        this.cinemaHallTitle = cinemaHallTitle;
        this.cinemaHallDescription = cinemaHallDescription;
    }
}
