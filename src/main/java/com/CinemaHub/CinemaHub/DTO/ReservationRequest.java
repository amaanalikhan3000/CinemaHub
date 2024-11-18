package com.CinemaHub.CinemaHub.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class ReservationRequest {
    private List<Integer> showIds;
    private List<Integer> seatNos;
    private Integer userId;
    private Integer showId;


    public ReservationRequest(List<Integer> seatNos, Integer userId, Integer showId) {
        this.seatNos = seatNos;
        this.userId = userId;
        this.showId = showId;
    }



}
