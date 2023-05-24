package com.multiplex.ticketBooking.entity;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false, referencedColumnName = "slotId")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "booking_user_id", nullable = false, referencedColumnName = "userId")
    private User user;

    private LocalDate bookingDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer noOfSeats;
}

