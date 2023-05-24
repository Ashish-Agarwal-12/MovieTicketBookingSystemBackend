package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.repository.HallRepository;
import com.multiplex.ticketBooking.service.HallServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hallServiceImpl implements HallServie {

    @Autowired
    private HallRepository hallRepository;
    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall getHallById(Long id) {
        return hallRepository.findById(id).get();
    }

    @Override
    public Hall updateHall(Hall hall, Long id) {
        Hall oldHall = hallRepository.findById(id).get();
        if(oldHall.getTotalCapacity() != hall.getTotalCapacity()) {
            oldHall.setTotalCapacity(hall.getTotalCapacity());
        }
        return hallRepository.save(oldHall);
    }

    @Override
    public Hall addHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}
