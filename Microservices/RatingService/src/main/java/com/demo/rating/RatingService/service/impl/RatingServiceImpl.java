package com.demo.rating.RatingService.service.impl;

import com.demo.rating.RatingService.entities.Rating;
import com.demo.rating.RatingService.repositories.RatingRepository;
import com.demo.rating.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        String ratingUserId = UUID.randomUUID().toString();
        rating.setRatingId(ratingUserId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getallRating() {

        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {

        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {

        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRatingById(String ratingId, Rating newRating) {
      /*  Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new RuntimeException("this id is not found"));
        rating.setRatingId(newRating.getRatingId());
        rating.setUserId(newRating.getUserId());
        rating.setHotelId(newRating.getHotelId());
        rating.setRating(newRating.getRating());
        rating.setFeedback(newRating.getFeedback());

        ratingRepository.save(rating);
        return rating;
*/

        Optional<Rating> byRating = ratingRepository.findById(ratingId);
/*
        Rating oldRating = null;
        if (byRating.isEmpty()) {
            new RuntimeException("user is not found ");
        } else {

            oldRating = byRating.get();
            oldRating.setRatingId(newRating.getRatingId());
            oldRating.setUserId(newRating.getUserId());
            oldRating.setHotelId(newRating.getHotelId());
            oldRating.setFeedback(newRating.getFeedback());

        }
        return oldRating;*/
        //  }
        Rating existingRating =ratingRepository.findById(ratingId).get();
        existingRating.setRatingId(newRating.getRatingId()!=null ? newRating.getRatingId() : existingRating.getRatingId());
        existingRating.setUserId(newRating.getUserId()!=null ? newRating.getUserId() : existingRating.getUserId());
        existingRating.setHotelId(newRating.getHotelId()!=null ? newRating.getHotelId() : existingRating.getHotelId());
        existingRating.setFeedback(newRating.getFeedback()!=null ? newRating.getFeedback() : existingRating.getFeedback());
        existingRating.setRating(existingRating.getRating());
        return ratingRepository.save(existingRating);
    }


    @Override
    public void deleteRatingById(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("this is is not found"));
        ratingRepository.delete(rating);


    }


}
