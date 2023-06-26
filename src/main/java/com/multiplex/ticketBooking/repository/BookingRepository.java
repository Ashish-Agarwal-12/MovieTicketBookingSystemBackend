package com.multiplex.ticketBooking.repository;

import com.multiplex.ticketBooking.entity.Booking;
import com.multiplex.ticketBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserUserId(Long id);
}
