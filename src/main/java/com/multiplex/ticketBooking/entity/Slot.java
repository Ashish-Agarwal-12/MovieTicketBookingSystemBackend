package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

//    @ManyToOne
//    @JoinColumn(name = "movie_id", nullable = false)
//    private Movies movie;

    @ManyToOne
    @JoinColumn(name = "hall_id",nullable = false)
    private Hall hall;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalDate slotDate;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Double amount;
}
