package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Hall;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HallServie {
    List<Hall> getAllHalls();

    Hall getHallById(Long id);

    Hall updateHall(Hall hall, Long id);

    Hall addHall(Hall hall);

    void deleteHall(Long id);
}
