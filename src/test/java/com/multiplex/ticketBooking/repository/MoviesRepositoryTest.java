package com.multiplex.ticketBooking.repository;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.entity.Slot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MoviesRepositoryTest {

    @Autowired
    private MoviesRepository moviesRepository;

    Movies movies;
    Hall hall1;
    Slot slot;

    @BeforeEach
    void setUp() {
        hall1 = Hall.builder()
                .hallId(1L)
                .hallName("PVR BNGLR")
                .totalCapacity(100)
                .address("Orion Mall")
                .build();

        slot = Slot.builder()
                .slotId(1L)
                .hall(hall1)
                .startTime(LocalTime.now())
                .slotDate(LocalDate.now())
                .capacity(100)
                .amount(180.00)
                .duration("2hrs 40 minutes")
                .build();

        List<Slot> slots = new ArrayList<>();
        slots.add(slot);

        movies = Movies.builder()
                .movieId(1L)
                .title("Main")
                .description("A romance thriller film")
                .genre("Romance, Action, thriller")
                .duration(180)
                .releaseDate(LocalDate.now())
                .slots(slots)
                .build();
    }

    @AfterEach
    void tearDown() {
        movies = null;
        moviesRepository.deleteAll();
    }

    // Test Case Success
    @Test
    void testFindByTitleContaining_Found() {
        List<Movies> moviesList = moviesRepository.findByTitleContaining("Main");
        assertThat(moviesList.get(0).getMovieId()).isEqualTo(movies.getMovieId());
        assertThat(moviesList.get(0).getReleaseDate()).isEqualTo(movies.getReleaseDate());
    }

    @Test
    void testFindByTitleContaining_NotFound() {
        List<Movies> moviesList = moviesRepository.findByTitleContaining("Main");
        assertThat(moviesList.isEmpty()).isTrue();
    }
}