package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.service.TicketService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TicketController {

    public static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    private TicketService ticketService;

    @GetMapping("/getTicketById/{ticketId}")
    public Ticket getTicketById(@PathVariable Long ticketId){
        logger.info("Getting all tickets");
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null){
            logger.info("No tickets found");
            return null;
        }
        logger.info("Getting all tickets");
        return ticket;
    }
    @GetMapping("/getAllTickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        logger.info("Retrieving ticket");
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

}
