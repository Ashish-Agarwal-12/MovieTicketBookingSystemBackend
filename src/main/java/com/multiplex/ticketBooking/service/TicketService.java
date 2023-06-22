package com.multiplex.ticketBooking.service;

import com.multiplex.ticketBooking.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(Long ticketId);

    List<Ticket> getAllTickets();
}
