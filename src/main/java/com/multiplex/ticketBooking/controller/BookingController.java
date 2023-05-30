package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Booking;
import com.multiplex.ticketBooking.entity.Ticket;
import com.multiplex.ticketBooking.exception.BookingNotConfirmedException;
import com.multiplex.ticketBooking.service.BookingService;
import com.multiplex.ticketBooking.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Ticket addBooking(@Valid @RequestBody Booking booking) throws BookingNotConfirmedException {
        Booking savedBooking = bookingService.addBooking(booking);
        if (savedBooking == null){
            throw new BookingNotConfirmedException("Booking Cannot Be Confirmed");
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
    public Booking updateBooking(@Valid @RequestBody Booking booking, @PathVariable Long id) {
        return bookingService.updateBooking(booking, id);
    }

    @PutMapping("/cancelBooking/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

}
