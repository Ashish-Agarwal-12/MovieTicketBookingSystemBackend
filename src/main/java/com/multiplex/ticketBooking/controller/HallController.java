package com.multiplex.ticketBooking.controller;


import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.service.HallService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    public static final Logger logger = LoggerFactory.getLogger(HallController.class);

    @PostMapping("/addHall")
    public Hall addHall(@Valid @RequestBody Hall hall){
        logger.info("Creating a new Hall");
        return hallService.addHall(hall);
    }

    @GetMapping("/getAllHalls")
    public List<Hall> getAllHalls() {
        logger.info("Retrieving all Halls");
        return hallService.getAllHalls();
    }

    @GetMapping("/getHallById/{id}")
    public Hall getHallById(@PathVariable Long id) {
        logger.info("Retrieving a particular Hall By id");
        return hallService.getHallById(id);
    }

    @PutMapping("/updateHall/{id}")
    public Hall updateHall(@RequestBody Hall hall, @PathVariable Long id) {
        logger.info("Updating the Hall By id");
        return hallService.updateHall(hall, id);
    }

    @DeleteMapping("/deleteHall/{id}")
    public void deleteHall(@PathVariable Long id) {
        logger.info("Deleting the hall");
        hallService.deleteHall(id);
    }
}
