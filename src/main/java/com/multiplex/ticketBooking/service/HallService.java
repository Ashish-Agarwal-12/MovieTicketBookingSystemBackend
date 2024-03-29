package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.payLoads.HallPostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HallService {
    List<Hall> getAllHalls();

    Hall getHallById(Long id);

    Hall updateHall(Hall hall, Long id);

    Hall addHall(Hall hall);

    String deleteHall(Long id);
}
