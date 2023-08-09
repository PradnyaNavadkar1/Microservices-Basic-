package com.demo.userservice.service.impl;

import com.demo.userservice.entities.Hotel;
import com.demo.userservice.entities.Rating;
import com.demo.userservice.entities.User;
import com.demo.userservice.exception.ResourceNotFoundException;
import com.demo.userservice.external.services.HotelService;
import com.demo.userservice.reposities.UserRepository;
import com.demo.userservice.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;



    @Autowired
    private HotelService hotelService;


    public void setHotelService(HotelService hotelService) {

        this.hotelService = hotelService;
    }



/*

    private WebClient.Builder webClient ;

    public UserServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient;
    }
    @Autowired
    public UserServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }
*/

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }


    @Override
    public User getUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id not found on the server " + userId));
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        LOGGER.info("{}", ratingOfUser);
        /* user.setRatings(ratingOfUser);
         return user;*/


       /* Rating[] ratingOfUser = webClient.baseUrl("http://RATING-SERVICE")
                .build().get()
                .uri("/ratings/users/" + user.getUserId())
                .retrieve()   //initiate the request and retrieve the response
                .bodyToMono(Rating[].class)   //specify the type of response you want
                .block();      // block the executions and wait for the response to be available*/

        List<Rating> ratings = Arrays.asList(ratingOfUser);

        List<Rating> listofRating = ratings.stream().map(rating -> {
/*         Hotel hotel= webClient.get()
                   .uri("http://HOTEL-SERVICE/hotels/"+rating.getHotelId())
                   .retrieve()
                   .bodyToMono(Hotel.class)
                    .block();*/
            /*ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            LOGGER.info("Response status code:{}", HttpStatus.CREATED);*/
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());
        user.setRatings(listofRating);
        return user;


    }
    }