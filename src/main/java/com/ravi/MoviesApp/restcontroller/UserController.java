package com.ravi.MoviesApp.restcontroller;

import com.ravi.MoviesApp.entity.Movie;
import com.ravi.MoviesApp.entity.User;
import com.ravi.MoviesApp.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<HashSet<Movie>> getUser(@PathVariable String email){
        return new ResponseEntity<HashSet<Movie>>(userService.watchListMovies(email), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HashSet<Movie>> addMovie(@RequestBody Map<String,String> payload){
        return  new ResponseEntity<HashSet<Movie>>(userService.watchListMovies(payload.get("email"),payload.get("imdbId"),payload.get("action")),HttpStatus.CREATED);

    }



}
