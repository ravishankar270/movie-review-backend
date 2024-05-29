package com.ravi.MoviesApp.entity;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate template;

    public  UserService (UserRepository repo){
        this.userRepository=repo;

    }

    public HashSet<Movie> watchListMovies(String email, String imdbId, String action){
        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent()){
            User newUser = new User(email);
            userRepository.save(newUser);
            return  newUser.getWatchList();
        }
        else if(imdbId != null) {
            User existingUser=user.get();
            // Create a query to find the user by imbdId
            Query query = new Query(Criteria.where("imdbId").is(imdbId));

            Movie movie=template.findOne(query,Movie.class);
            HashSet<Movie> existingWatchlist = existingUser.getWatchList();
            if( action != null && action.equals("remove")){
                existingWatchlist.remove(movie);
            }else {
                existingWatchlist.add(movie);
            }
            existingUser.setWatchList(existingWatchlist);
            userRepository.save(existingUser);
            return existingUser.getWatchList();
        }else {
            return  user.get().getWatchList();
        }


    }

    public  HashSet<Movie> watchListMovies(String email){
        HashSet<Movie> watchList = this.watchListMovies(email,null,null);
        return  watchList;
    }
}
