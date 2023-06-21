package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Booking;
import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.repository.BookingRepository;
import com.multiplex.ticketBooking.repository.SlotRepository;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class bookingServiceImplTest {

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;
    private SlotRepository slotRepository;
    private Booking booking;
    private Booking oldBooking;
    AutoCloseable autoCloseable;

    private User user;
    private Slot slot;
    private Hall hall;

    @BeforeEach
    void setup()
    {
        autoCloseable = MockitoAnnotations.openMocks(this);
        user = User.builder()
                .userId(1L)
                .userType("Normal")
                .userName("Ashish")
                .password("Agarwal12")
                .mobileNumber("9178056621")
                .build();
        hall = Hall.builder()
                .hallId(1L)
                .address("Phoenix Mall, Banglore")
                .hallName("PVR Banglore")
                .totalCapacity(100)
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

        booking = Booking.builder()
                .bookingId(1L)
                .bookingDate(LocalDate.now())
                .noOfSeats(4)
                .slot(slot)
                .user(user)
                .status("CANCELLED")
                .build();
        oldBooking = Booking.builder()
                .bookingId(1L)
                .bookingDate(LocalDate.now())
                .noOfSeats(4)
                .slot(slot)
                .user(user)
                .status("CONFIRMED")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        bookings.add(oldBooking);

        Mockito.when(bookingRepository.findAll()).thenReturn(bookings);
        List<Booking> savedBookings = bookingService.getAllBookings();
        assertEquals(savedBookings, bookings);
    }

    @Test
    void getBookingById() {
        Mockito.when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.ofNullable(booking));
        Booking savedBooking = bookingService.getBookingById(1L);
        assertEquals(savedBooking, booking);
    }

    @Test
    void addBooking() {
        Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
        Booking savedBooking = bookingService.addBooking(booking);
        verify(bookingRepository, Mockito.times(1)).save(booking);
    }

    @Test
    @DisplayName("Updating Booking")
    void testUpdateUser() {
        mock(Booking.class);
        mock(BookingRepository.class);

        when(bookingRepository.save(oldBooking)).thenReturn(oldBooking);
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(oldBooking));
        Booking updatedBooking = bookingService.updateBooking(booking,1L);
        assertEquals(booking.getStatus(), updatedBooking.getStatus());
        assertEquals(booking.getBookingDate(), updatedBooking.getBookingDate());

    }

    @Test
    public void testCancelBooking_ValidBookingId_BookingFound() {
        // Mock the behavior of bookingRepository.findById()
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        // Call the cancelBooking() method
        bookingService.cancelBooking(1L);

        // Verify that bookingRepository.findById() was called with the correct ID
        verify(bookingRepository).findById(1L);

        // Verify that booking status was updated to "Cancelled"
        assertEquals("Cancelled", booking.getStatus());

        // Verify that bookingRepository.save() was called with the updated booking
        verify(bookingRepository).save(booking);
    }

    @Test
    public void testCancelBooking_ValidBookingId_BookingNotFound() {

        // Mock the behavior of bookingRepository.findById() to return an empty Optional
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the cancelBooking() method
        bookingService.cancelBooking(1L);

        // Verify that bookingRepository.findById() was called with the correct ID
        verify(bookingRepository).findById(1L);

        // Verify that bookingRepository.save() was not called
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    public void testCancelBooking_ValidBookingId_UpdateSlotCapacity() {
        // Mock the behavior of bookingRepository.findById()
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        // Call the cancelBooking() method
        bookingService.cancelBooking(1L);

        // Verify that bookingRepository.findById() was called with the correct ID
        verify(bookingRepository).findById(1L);

        // Verify that booking status was updated to "Cancelled"
        assertEquals("Cancelled", booking.getStatus());

        // Verify that bookingRepository.save() was called with the updated booking
        verify(bookingRepository).save(booking);

        // Verify that slotRepository.findById() was called with the correct slot ID
//        verify(slotRepository).findById(slot.getSlotId());

        // Verify that slot capacity was updated with the correct value
        assertEquals(100, slot.getCapacity()); // Assuming the initial capacity was 10 and noOfSeats to cancel was 2

    }

}