package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.exception.MovieNotCreatedException;
import com.multiplex.ticketBooking.exception.MovieNotFoundException;
import com.multiplex.ticketBooking.service.MoviesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @PostMapping("/addMovie")
    public Movies addMovie(@Valid @RequestBody Movies movie) throws MovieNotCreatedException {
        Movies createMovie = moviesService.addMovie(movie);
        if(createMovie == null){
            throw new MovieNotCreatedException("This Movie could not be created");
        }
        return createMovie;
    }

    @GetMapping("/getAllMovies")
    public List<Movies> getAllMovies() {
        return moviesService.getAllMovies();
    }

    @GetMapping("/getMovieById/{id}")
    public Movies getMovieById(@PathVariable Long id) {
        return moviesService.getMovieById(id);
    }

    @PutMapping("/updateMovie/{id}")
    public Movies updateMovie(@Valid @RequestBody Movies movie, @PathVariable Long id){
        return moviesService.updateMovie(movie, id);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable Long id) {
        moviesService.deleteMovie(id);
    }

    @GetMapping("/searchMovieByName")
    public List<Movies> searchMovieByName(@Valid @RequestParam("title") String movieName) throws MovieNotFoundException {
        List<Movies> moviesList = moviesService.searchMovieByName(movieName);
        if(moviesList.size() == 0){
            //Throwing MovieNotFoundException
            throw new MovieNotFoundException("Movie doesn't exist.");
        }
        return moviesList;
    }
}
