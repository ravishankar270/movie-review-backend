package com.ravi.MoviesApp.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate template;
    @Autowired
    public  ReviewService (ReviewRepository rRepository){
        this.reviewRepository=rRepository;
    }

    public  Review createReview(String reviewBody,String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));
        template.update(Movie.class).matching(Criteria.where("imdbId").is(imdbId)).
                apply(new Update().push("reviewIds").value(review)).first();
        return  review;

    }

}
