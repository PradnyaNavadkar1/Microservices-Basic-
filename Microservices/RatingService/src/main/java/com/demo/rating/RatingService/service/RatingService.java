package com.demo.rating.RatingService.service;

import com.demo.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
     //Create
    Rating create (Rating rating);

    //get all Rating
    List<Rating> getallRating();

    //get allby userId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotelid
    List<Rating> getRatingByHotelId(String hotelId);



    Rating updateRatingById(String ratingId, Rating newRating);

    void deleteRatingById(String ratingId);
}
