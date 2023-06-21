package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.exception.SlotNotFoundException;
import com.multiplex.ticketBooking.repository.HallRepository;
import com.multiplex.ticketBooking.repository.SlotRepository;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.HallService;
import com.multiplex.ticketBooking.service.SlotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class slotServiceImplTest {

    @MockBean
    private SlotRepository slotRepository;

    @Mock
    private HallRepository hallRepository;

    @Autowired
    private SlotService slotService;
    AutoCloseable autoCloseable;
    private Slot slot;
    private Slot oldSlot;
    private Hall hall;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        hall = Hall.builder()
                .hallId(1L)
                .hallName("PVR BNGLR")
                .totalCapacity(100)
                .address("Orion Mall")
                .build();

        slot = Slot.builder()
                .slotId(1L)
                .hall(hall)
                .startTime(LocalTime.now())
                .slotDate(LocalDate.now())
                .capacity(100)
                .amount(180.00)
                .duration("2hrs 40 minutes")
                .build();
        oldSlot = Slot.builder()
                .slotId(1L)
                .hall(hall)
                .startTime(LocalTime.now())
                .slotDate(LocalDate.now())
                .capacity(120)
                .amount(200.00)
                .duration("2hrs 38 minutes")
                .build();
    }

    @Test
    void testGetAllSlots() {
        mock(Slot.class);
        mock(SlotRepository.class);
        when(slotRepository.findAll()).thenReturn(new ArrayList<Slot>(Collections.singleton(slot)));
        assertThat(slotService.getAllSlots().get(0).getSlotId()).isEqualTo(slot.getSlotId());
    }

    @Test
    void testGetSlotsById() throws SlotNotFoundException {
        mock(Slot.class);
        mock(SlotRepository.class);
        when(slotRepository.findById(1L)).thenReturn(Optional.ofNullable(slot));
        assertThat(slotService.getSlotsById(1L).getSlotId()).isEqualTo(slot.getSlotId());
    }

    @Test
    void testUpdateSlots() {
        mock(Slot.class);
        mock(SlotRepository.class);

        when(slotRepository.save(oldSlot)).thenReturn(oldSlot);
        when(slotRepository.findById(1L)).thenReturn(Optional.of(oldSlot));
        Slot updatedSlot = slotService.updateSlots(slot,1L);

        assertEquals(slot.getCapacity(), updatedSlot.getCapacity());
        assertEquals(slot.getStartTime(), updatedSlot.getStartTime());
        assertEquals(hall, updatedSlot.getHall());
        verify(slotRepository).save(updatedSlot);
    }

    @Test
    void testDeleteSlot() throws DataIntegrityViolationException {
        mock(Slot.class);
        mock(SlotRepository.class);

        doThrow(DataIntegrityViolationException.class)
                .when(slotRepository)
                .deleteById(1L);
        assertThrows(DataIntegrityViolationException.class, () -> slotService.deleteSlot(1L));
        verify(slotRepository).deleteById(1L);
    }
}