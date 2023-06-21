package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.repository.TicketRepository;
import com.multiplex.ticketBooking.service.TicketService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;
    AutoCloseable autoCloseable;
    Ticket ticket1;
    Ticket ticket2;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        ticket1 = Ticket.builder()
                .ticketId(1L)
                .userName("Ashish Agarwal")
                .date(LocalDate.now())
                .noOfSeats(2)
                .startTime(LocalTime.now())
                .totalAmount(250.00)
                .build();
        ticket2 = Ticket.builder()
                .ticketId(2L)
                .userName("Ashish")
                .date(LocalDate.now())
                .noOfSeats(3)
                .startTime(LocalTime.now())
                .totalAmount(750.00)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveTicket() {
        mock(Ticket.class);
        mock(TicketRepository.class);

        when(ticketRepository.save(ticket1)).thenReturn(ticket1);
        assertThat(ticketService.saveTicket(ticket1)).isEqualTo(ticket1);
    }

    @Test
    void testGetAllTicketsByUserName(){
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        Mockito.when(ticketRepository.findAllByUserName("Ashish")).thenReturn(tickets);
        List<Ticket> listOfTicketsContainingTicketName = ticketService.getAllTicketsByUserName("Ashish");
        assertEquals(listOfTicketsContainingTicketName, tickets);
    }

    @Test
    void testGetTicketById() {
        mock(Ticket.class);
        mock(TicketRepository.class);

        when(ticketRepository.findById(1L)).thenReturn(Optional.ofNullable(ticket1));
        assertThat(ticketService.getAllTicketsById(1L).getTicketId()).isEqualTo(ticket1.getTicketId());
    }
}