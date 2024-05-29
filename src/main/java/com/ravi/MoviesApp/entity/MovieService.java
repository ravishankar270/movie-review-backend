package com.ravi.MoviesApp.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository mRepository){
        this.movieRepository=mRepository;
    }
    public List<Movie> allMovies(){
        return  movieRepository.findAll();
    }

    public Optional<Movie> findMovie (String imdbID){
        return  movieRepository.findByImdbId(imdbID);
    }
}
