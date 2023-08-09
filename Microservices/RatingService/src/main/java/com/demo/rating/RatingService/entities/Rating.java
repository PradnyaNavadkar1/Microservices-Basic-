package com.demo.rating.RatingService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_rating")
public class Rating {
    @Id
    @Column(name="RatingId")
    private String ratingId;

    @Column(name="UserId")
    private String userId;
    @Column(name="HotelId")
    private String hotelId;
    @Column(name="Rating")
    private int rating;
    @Column(name="FeedBack")
    private String feedback;
    @Override
    public String toString() {
        return "Rating{" +
                "ratingId='" + ratingId + '\'' +
                ", userId='" + userId + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", rating=" + rating +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
