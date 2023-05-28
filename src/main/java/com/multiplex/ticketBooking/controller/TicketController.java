package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/getAllTicketsByUserName")
    public List<Ticket> getAllTicketsByUserName(@Valid @RequestParam("userName") String userName){
        List<Ticket> tickets = ticketService.getAllTicketsByUserName(userName);
        if (tickets.isEmpty()){
            return null; //throw no tickets with this username
        }
        return tickets;
    }

    @GetMapping("/getTicketById")
    public Ticket getAllTicketsById(@PathVariable Long ticketId){
        Ticket ticket = ticketService.getAllTicketsById(ticketId);
        if (ticket == null){
            return null; //throw no ticket found
        }
        return ticket;
    }

}
