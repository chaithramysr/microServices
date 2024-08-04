package com.example.userService.externalServices;

import com.example.userService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/getHotelDetailsByHotelIds")
    List<Hotel> getHotelDetailsByHotelIds(List<String> hotelIds);
}
