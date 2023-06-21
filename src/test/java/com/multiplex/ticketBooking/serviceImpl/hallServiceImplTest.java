package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.repository.HallRepository;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.HallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class hallServiceImplTest {

    private Hall hall;
    private Hall oldHall;

    @MockBean
    private HallRepository hallRepository;

    @Autowired
    private HallService hallService;

    @BeforeEach
    void setup()
    {
        hall = Hall.builder()
                .hallId(1L)
                .hallName("PVR BNGLR")
                .totalCapacity(100)
                .address("Orion Mall")
                .build();
        oldHall = Hall.builder()
                .hallId(1L)
                .hallName("PVR BNGLR")
                .totalCapacity(96)
                .address("Orion Mall")
                .build();
    }

    @Test
    void getAllHalls() {
        List<Hall> halls = new ArrayList<>();
        halls.add(hall);
        halls.add(oldHall);

        Mockito.when(hallRepository.findAll()).thenReturn(halls);
        List<Hall> savedHall = hallRepository.findAll();
        assertEquals(savedHall, halls);
    }

    @Test
    void getHallById() {
        mock(Hall.class);
        mock(HallRepository.class);
        when(hallRepository.findById(1L)).thenReturn(Optional.ofNullable(hall));
        assertThat(hallService.getHallById(1L).getHallId()).isEqualTo(hall.getHallId());

    }

    @Test
    void testAddHall() {
        mock(Hall.class);
        mock(HallRepository.class);
        when(hallRepository.save(hall)).thenReturn(hall);
        assertThat(hallService.addHall(hall)).isEqualTo(hall);
    }

    @Test
    void testDeleteHall() {
        mock(Hall.class);
        mock(HallRepository.class);
        doNothing().when(hallRepository).deleteById(1L);
        String result = hallService.deleteHall(1L);
        verify(hallRepository).deleteById(1L);
        assertEquals("Deleted Successfully",result);
    }

    @Test
    @DisplayName("Update Hall")
    void testUpdateHall() {
        mock(Hall.class);
        mock(HallRepository.class);

        when(hallRepository.save(oldHall)).thenReturn(oldHall);
        when(hallRepository.findById(1L)).thenReturn(Optional.of(oldHall));
        Hall updatedHall = hallService.updateHall(hall,1L);
        assertEquals(hall.getTotalCapacity(), updatedHall.getTotalCapacity());

    }
}