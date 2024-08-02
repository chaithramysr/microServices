package com.example.hotelService.service;

import com.example.hotelService.entity.Hotel;
import com.example.hotelService.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public String addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return "Hotel added successfully";
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).get();
    }

    public String updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return "Hotel updated successfully";
    }

    public String deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
        return "Hotel deleted successfully";
    }
}
