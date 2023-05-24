package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.Movies;
import com.multiplex.ticketBooking.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @PostMapping("/addMovie")
    public Movies addMovie(@RequestBody Movies movie){
        return moviesService.addMovie(movie);
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
    public Movies updateMovie(@RequestBody Movies movie, @PathVariable Long id){
        return moviesService.updateMovie(movie, id);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable Long id) {
        moviesService.deleteMovie(id);
    }

    @GetMapping("/searchMovieByName")
    public List<Movies> searchMovieByName(@RequestParam("title") String movieName){
        List<Movies> moviesList = moviesService.searchMovieByName(movieName);
        if(moviesList.size() == 0){
            //throw new MovieNotFoundException("")
        }
        return moviesList;
    }
}
