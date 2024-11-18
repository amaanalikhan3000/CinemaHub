package com.CinemaHub.CinemaHub.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "seat_no")
    private int seatNo;

    @Column(name = "price")
    private double price;

    @Column(name = "is_reserved")
    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private MovieShow movieShow; // Link to MovieShow (show_id)


    @Version // Enables optimistic locking
    private Long version;


}


