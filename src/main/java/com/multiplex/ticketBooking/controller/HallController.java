package com.multiplex.ticketBooking.controller;


import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    @PostMapping("/addHall")
    public Hall addHall( @RequestBody Hall hall){
        return hallService.addHall(hall);
    }

    @GetMapping("/getAllHalls")
    public List<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @GetMapping("/getHallById/{id}")
    public Hall getHallById(@PathVariable Long id) {
        return hallService.getHallById(id);
    }

    @PutMapping("/updateHall/{id}")
    public Hall updateHall(@RequestBody Hall hall, @PathVariable Long id) {
        return hallService.updateHall(hall, id);
    }

    @DeleteMapping("/deleteHall/{id}")
    public void deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
    }
}
