package com.multiplex.ticketBooking.serviceImpl;

import com.multiplex.ticketBooking.entity.Hall;
import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.entity.Slot;
import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.repository.MoviesRepository;
import com.multiplex.ticketBooking.repository.UserRepository;
import com.multiplex.ticketBooking.service.MoviesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class moviesServiceImplTest {

    @Autowired
    private MoviesService moviesService;
    private Movies movies;
    private Slot slot;
    private Hall hall1;
    private Movies oldMovies;
    AutoCloseable autoCloseable;

    @MockBean
    private MoviesRepository moviesRepository;

    @BeforeEach

    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
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
                .title("Main Hoon Na")
                .description("A romance thriller film")
                .genre("Romance, Action, thriller")
                .duration(180)
                .releaseDate(LocalDate.now())
                .slots(slots)
                .build();
        oldMovies = Movies.builder()
                .movieId(1L)
                .title("Main")
                .description("A thriller film")
                .genre("Romance, Action, thriller")
                .duration(180)
                .releaseDate(LocalDate.now())
                .slots(slots)
                .build();
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testAddMovie() {
        mock(Movies.class);
        mock(MoviesRepository.class);
        when(moviesRepository.save(movies)).thenReturn(movies);
        assertThat(moviesService.addMovie(movies)).isEqualTo(movies);
    }

    @Test
    void testGetAllMovies() {
        mock(Movies.class);
        mock(MoviesRepository.class);
        when(moviesRepository.findAll()).thenReturn(new ArrayList<Movies>(Collections.singleton(movies)));
        assertThat(moviesService.getAllMovies().get(0).getTitle()).isEqualTo(movies.getTitle());
    }

    @Test
    void testGetMovieById() {
        mock(Movies.class);
        mock(MoviesRepository.class);
        when(moviesRepository.findById(1L)).thenReturn(Optional.ofNullable(movies));
        assertThat(moviesService.getMovieById(1L).getMovieId()).isEqualTo(movies.getMovieId());
    }

    @Test
    void testDeleteMovie() {
        mock(Movies.class);
        mock(MoviesRepository.class);
        doNothing().when(moviesRepository).deleteById(1L);
        String result = moviesService.deleteMovie(1L);
        verify(moviesRepository).deleteById(1L);
        assertEquals("Deleted Successfully",result);
    }

    @Test
    void testUpdateMovie() {
        mock(Movies.class);
        mock(MoviesRepository.class);

        when(moviesRepository.save(movies)).thenReturn(oldMovies);
        when(moviesRepository.findById(1L)).thenReturn(Optional.of(oldMovies));
        Movies updatedMovie = moviesService.updateMovie(movies, 1L);
        assertEquals(movies.getDescription(), updatedMovie.getDescription());
        assertEquals(movies.getGenre(), updatedMovie.getGenre());
        assertEquals(movies.getDuration(), updatedMovie.getDuration());
        assertEquals(movies.getReleaseDate(), updatedMovie.getReleaseDate());
        assertEquals(movies.getTitle(), updatedMovie.getTitle());
    }

    @Test
    void searchMovieByName() {
        List<Movies> movieList = new ArrayList<>();
        movieList.add(movies);
        movieList.add(oldMovies);
        Mockito.when(moviesRepository.findByTitleContaining("Main")).thenReturn(movieList);
        List<Movies> listOfMoviesContainingMovieName = moviesService.searchMovieByName("Main");
        assertEquals(listOfMoviesContainingMovieName, movieList);
    }
}