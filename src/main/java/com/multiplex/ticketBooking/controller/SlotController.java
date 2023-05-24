package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SlotController {
    @Autowired
    private SlotService slotService;

    @GetMapping("/getAllSlots")
    public List<Slot> getAllSlots() {
        return  slotService.getAllSlots();
    }

    @GetMapping("/getSlotById/{id}")
    public Slot getSlotsById(@PathVariable Long id) {
        return slotService.getSlotsById(id);
    }

    @PutMapping("/updateSlots")
    public Slot updateSlots(@RequestBody Slot slot, @PathVariable Long id) {
        return slotService.updateSlots(slot, id);
    }

    @DeleteMapping("/deleteSlot/{id}")
    public void deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
    }
}
