package com.example.hotelService.repository;

import com.example.hotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

    @Query("SELECT hotel FROM Hotel hotel WHERE hotel.id IN :hotelIds")
    List<Hotel> fetchByHotelIds(@Param("hotelIds") List<String> hotelIds);
}
