package com.demo.rating.RatingService.repositories;

import com.demo.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {

    //Custom finder method
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
