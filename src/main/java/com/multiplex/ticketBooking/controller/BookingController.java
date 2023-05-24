package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Booking;
import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.service.BookingService;
import com.multiplex.ticketBooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookingById/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/addBooking")
    public Ticket addBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.addBooking(booking);
        if (savedBooking == null){
            return null; //throw new BookingNotConfirmedException
        }

        Ticket ticket = Ticket.builder()
                .date(booking.getSlot().getSlotDate())
                .noOfSeats(booking.getNoOfSeats())
                .startTime(booking.getSlot().getStartTime())
                .userName(booking.getUser().getUserName())
                .totalAmount(booking.getNoOfSeats()*booking.getSlot().getAmount()).build();
        return ticketService.saveTicket(ticket);
    }

    @PutMapping("/updateBooking/{id}")
    public Booking updateBooking(@RequestBody Booking booking, @PathVariable Long id) {
        return bookingService.updateBooking(booking, id);
    }

    @PutMapping("/cancelbooking/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }


}
