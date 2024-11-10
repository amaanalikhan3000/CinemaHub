package com.CinemaHub.CinemaHub.DTO;

import lombok.Data;

import java.util.List;


@Data
public class ReservationRequest {
    private List<Integer> showIds;

    private List<Integer> seatNos;
    private Long userId;
    private Long showId;



}
