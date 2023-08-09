package com.demo.userservice.external.services;

import com.demo.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
    //getMapping
    @GetMapping("/rating")
    Rating getRating();
    // PostMapping
    @PostMapping("/ratings/{ratingId}")
    Rating createRating(Rating values);
    //PutMapping
    @PutMapping("/rating/{ratinngId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);
    //DeleteMapping

    @DeleteMapping("/rating/{ratingId}")
    void deleteRating(@PathVariable String ratingId);


}
