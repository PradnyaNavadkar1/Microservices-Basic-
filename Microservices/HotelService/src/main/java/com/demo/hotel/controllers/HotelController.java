package com.demo.hotel.controllers;


import com.demo.hotel.entities.Hotel;
import com.demo.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //Create

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    //getSingle
    @PreAuthorize("hasAuthority('SCOPE_internal')||hasAuthority('Admin')")
    @GetMapping ("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }
    //getall
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){

        return ResponseEntity.ok(hotelService.getAll());
    }

}
