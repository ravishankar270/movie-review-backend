package com.ravi.MoviesApp.restcontroller;

import com.ravi.MoviesApp.entity.Review;
import com.ravi.MoviesApp.entity.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload){
        return  new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.CREATED);
    }
}
