package com.demo.userservice.controllers;

import com.demo.userservice.entities.User;
import com.demo.userservice.service.UserService;
import com.demo.userservice.service.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RefreshScope
@RestController
@RequestMapping("/users")
public class  UserController {

    @Autowired
    private UserService userservice;
    private  Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @PostMapping
    public ResponseEntity<User> CreateUser( @RequestBody User user){
        User newuser= userservice.saveUser(user);
           return ResponseEntity.status(HttpStatus.CREATED).body(newuser);
    }

    @GetMapping("/{userId}")
    //@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService" , fallbackMethod ="ratingHotelFallBack")
    public ResponseEntity<User> getSingleUserId(@PathVariable String userId){
        LOGGER.info("Get Single User Handler: UserController");
        User user=userservice.getUserId(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userservice.getAllUser();
        return  ResponseEntity.ok(allUser);
    }

    //Creating fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){
         int retryCount=1;
         ex.printStackTrace();
        //LOGGER.info("Fallback ie executed because service is down: ", ex.getMessage());
        LOGGER.info("Retry count:{}", retryCount);

        retryCount++;
        User user = User.builder()
                .email("prodapt.com")
                .name("Dummy")
                .about("this user is Created dummy because some service is down")
                .userId("12341")
                .build();


        return new ResponseEntity<>(user,HttpStatus.OK);




    }







}
