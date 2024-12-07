package com.CinemaHub.CinemaHub.Entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movie")

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    @NotBlank(message = "Title is required and cannot be blank")
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    @NotBlank(message = "Description is required and cannot be blank")
    @Size(min = 10,max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationInMin;

    @NotBlank(message = "Language is required and cannot be blank")
    private String language;

    @NotNull(message = "Release date is required")
    private LocalDateTime releaseDate;

    // Not reqd
    private String country;
    // not reqd
    private String genre;

}
