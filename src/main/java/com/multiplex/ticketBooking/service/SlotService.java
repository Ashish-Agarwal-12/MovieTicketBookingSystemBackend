package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Slot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SlotService {
    List<Slot> getAllSlots();

    Slot getSlotsById(Long id);

    void deleteSlot(Long id);

    Slot updateSlots(Slot slot, Long id);
}
