package com.ravi.MoviesApp.restcontroller;

import com.ravi.MoviesApp.entity.Movie;
import com.ravi.MoviesApp.entity.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService mService){
        this.movieService=mService;
    }
    @GetMapping
    public ResponseEntity<List<Movie>> allMovies() {
       return  new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public  ResponseEntity<Optional<Movie>> singleMovie(@PathVariable String imdbId){
        return  new ResponseEntity<Optional<Movie>>(movieService.findMovie(imdbId),HttpStatus.OK);
    }
}
