package com.CinemaHub.CinemaHub.Services;


import com.CinemaHub.CinemaHub.DTO.MovieShowDetailsDTO;
import com.CinemaHub.CinemaHub.Entity.MovieShow;
import com.CinemaHub.CinemaHub.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class showService {



    @Autowired
    private ShowRepo showRepo;

    public Optional<MovieShow> getShowsByMovieId(Integer movieId) {
        return showRepo.findById(movieId);
    }

//    public List<MovieShowDetailsDTO> getMovieShows(String title) {
//        return showRepo.findByTitle(title);
//    }

    public List<MovieShowDetailsDTO> mapToMovieShowDetailsDTO(List<Object[]> results) {
        List<MovieShowDetailsDTO> dtoList = new ArrayList<>();
        for (Object[] result : results) {
            String movieTitle = (String) result[0];
            String movieDescription = (String) result[1];
            int movieDuration = (Integer) result[2];
            String movieLanguage = (String) result[3];
            LocalDate movieReleaseDate = convertTimestampToLocalDate(result[4]);
            Long showId = convertIntegerToLong(result[5]);
            LocalDateTime startTime = convertTimestampToLocalDateTime(result[6]);
            LocalDateTime endTime = convertTimestampToLocalDateTime(result[7]);
            String cinemaName = (String) result[8];
            String cinemaLocation = (String) result[9];
            String cinemaHallTitle = (String) result[10];
            String cinemaHallDescription = (String) result[11];

            // Create DTO and add it to the list
            MovieShowDetailsDTO dto = new MovieShowDetailsDTO(movieTitle, movieDescription, movieDuration,
                    movieLanguage, movieReleaseDate.atStartOfDay(), showId, startTime, endTime, cinemaName, cinemaLocation,
                    cinemaHallTitle, cinemaHallDescription);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private LocalDateTime convertTimestampToLocalDateTime(Object timestampObject) {
        if (timestampObject != null && timestampObject instanceof Timestamp) {
            return ((Timestamp) timestampObject).toLocalDateTime(); // Correct conversion
        }
        return null;
    }

    private Long convertIntegerToLong(Object integerObject) {
        if (integerObject != null && integerObject instanceof Integer) {
            return Long.valueOf((Integer) integerObject); // Convert Integer to Long
        }
        return null;
    }

    private LocalDate convertTimestampToLocalDate(Object timestampObject) {
        if (timestampObject != null && timestampObject instanceof Timestamp) {
            return ((Timestamp) timestampObject).toLocalDateTime().toLocalDate();
        }
        return null;
    }

//    public List<MovieShowDetailsDTO> getMovieShows(String title) {
//        return showRepo.findMovieShowsByTitle(title);
//    }

    // Service method to fetch and map movie show details
    public List<MovieShowDetailsDTO> getMovieShowDetails(String title) {
        // Fetch results from the repository
        List<Object[]> results = showRepo.findMovieShowDetailsByTitle(title);
        // Map the results to DTOs
        return mapToMovieShowDetailsDTO(results);
    }




}
