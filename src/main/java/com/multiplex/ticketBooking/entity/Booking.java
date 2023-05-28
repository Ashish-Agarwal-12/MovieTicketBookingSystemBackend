package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @NotBlank(message = "Please Enter a valid slot")
    @Size(min = 1)
    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false, referencedColumnName = "slotId")
    private Slot slot;

    @NotBlank(message = "Please Enter the User Name")
    @ManyToOne
    @JoinColumn(name = "booking_user_id", nullable = false, referencedColumnName = "userId")
    private User user;

    @NotBlank(message = "Please Enter a booking Date")
    @FutureOrPresent
    private LocalDate bookingDate;

    @Column(nullable = false)
    private String status;

    @Size(min = 1)
    @Column(nullable = false)
    private Integer noOfSeats;
}

