package com.demo.hotel.services.impl;

import com.demo.hotel.entities.Hotel;
import com.demo.hotel.exceptions.ResourceNotFountException;
import com.demo.hotel.repositires.HotelRepository;
import com.demo.hotel.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class HotelServiceimpl implements HotelService {

   @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel create(Hotel hotel) {
     String hotelId =UUID.randomUUID().toString();
     hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> allHotels = hotelRepository.findAll();
        return allHotels;
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFountException("hotel with given id not found !!"));
    }
}
