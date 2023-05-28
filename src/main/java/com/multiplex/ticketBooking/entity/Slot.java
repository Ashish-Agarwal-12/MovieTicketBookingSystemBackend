package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Please Enter the Corresponding hall where movie needs to be Mapped")
    @ManyToOne
    @JoinColumn(name = "hall_id",nullable = false)
    private Hall hall;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalTime startTime;

    @NotBlank(message = "Please Enter the slot date")
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate slotDate;

    @NotBlank(message = "Please Enter the Duration of the movie in Minutes.")
    @Size(min = 90, max = 210)
    @Column(nullable = false)
    private String duration;

    @Size(min = 60, max = 120)
    @Column(nullable = false)
    private Integer capacity;

    @NotBlank(message = "Please Enter the movie Fare")
    @Min(160)
    @Column(nullable = false)
    private Double amount;
}
