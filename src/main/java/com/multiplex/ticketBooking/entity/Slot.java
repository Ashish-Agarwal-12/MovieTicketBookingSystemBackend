package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @Column(unique = true)
    @NotNull(message = "Please Enter the Slot Time")
    @FutureOrPresent(message = "Please Enter the Present Time/ Future Time")
    private LocalTime startTime;

    @NotNull(message = "Please Enter the Slot Date")
    @FutureOrPresent(message = "Please Enter Today's date / Future Date")
    private LocalDate slotDate;

    @NotEmpty(message = "Please Enter the duration of the movie")
    private String duration;

    @NotNull(message = "Please Enter the total Capacity")
    @Min(value = 60, message = "Capacity Should be minimum 60")
    private Integer capacity;

    @NotNull(message = "Please Enter the amount")
    private Double amount;
}
