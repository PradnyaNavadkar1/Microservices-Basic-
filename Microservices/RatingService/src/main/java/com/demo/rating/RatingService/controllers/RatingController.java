package com.demo.rating.RatingService.controllers;

import com.demo.rating.RatingService.entities.Rating;
import com.demo.rating.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
   //Create Rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));

    }
    @GetMapping
   public ResponseEntity<List<Rating>> getRating(){
        
        return ResponseEntity.ok(ratingService.getallRating());
   }

   @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingBYUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingBYHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }


    @PutMapping("/{ratingId}") //uriTemplate variable
    public ResponseEntity<Rating> updateRatingById(@PathVariable String ratingId, @RequestBody Rating rating){
       /* Rating newRating = ratingService.updateRatingById(ratingId, rating);*/
        return new ResponseEntity<>(ratingService.updateRatingById(ratingId, rating),HttpStatus.OK);

        /*return ResponseEntity.ok(newRating);*/
    }

   @DeleteMapping("/{ratingId}")
   public ResponseEntity<List<Rating>> deleteRatingById( @PathVariable String ratingId){
       ratingService.deleteRatingById(ratingId);
    List<Rating> ratings = getRating().getBody();
     System.out.println(ratings);
       return new ResponseEntity<>(ratings,HttpStatus.OK);
   }



}
