package com.demo.hotel.services;

import com.demo.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //Create
    Hotel create(Hotel hotel);
    //getall
    List<Hotel> getAll();

    //getSingle
    Hotel get(String id);

}
