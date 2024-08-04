package com.example.userService.service;

import com.example.userService.entity.Hotel;
import com.example.userService.entity.Rating;
import com.example.userService.entity.User;
import com.example.userService.externalServices.HotelService;
import com.example.userService.externalServices.RatingService;
import com.example.userService.repository.UserRepository;
import com.jsoniter.JsonIterator;
import com.jsoniter.spi.TypeLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;


    public String addUser(User user) {
        userRepository.save(user);
        return "User added successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        //Fetching user details based on userId
        User userDetails = userRepository.findById(userId).get();

        //Fetching user rating details based on userId
        String ratingUrl = "http://RATING-SERVICE/ratings/getRatingByUserId/"+userId;
        ResponseEntity<String> exchange = restTemplate.exchange(ratingUrl, HttpMethod.GET, null, String.class);
        List<Rating> ratings = JsonIterator.deserialize(exchange.getBody(), new TypeLiteral<List<Rating>>() {});

        //collect all hotel ids in rating details
        List<String> hotelIdsList = ratings.stream().map(rating -> rating.getHotelId()).toList();
        //Fetching Hotel details based on hotel ids
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<List<String>> httpEntity =new HttpEntity<>(hotelIdsList,httpHeaders);
        String hotelUrl="http://Hotel-SERVICE/hotels/getHotelDetailsByHotelIds";
        ResponseEntity<String> hotelExhange = restTemplate.exchange(hotelUrl, HttpMethod.POST, httpEntity, String.class);
        List<Hotel> hotelDetails= JsonIterator.deserialize(hotelExhange.getBody(), new TypeLiteral<List<Hotel>>() {});
        Map<String, Hotel> hotelIdAndHotelMap = hotelDetails.stream()
                .collect(Collectors.toMap(Hotel::getId, hotelData -> hotelData));
        ratings.forEach(rating -> rating.setHotel(hotelIdAndHotelMap.get(rating.getHotelId())));

        userDetails.setRatings(ratings);
        return userDetails;
    }

    public User getUserUsingFeign(String userId) {
        //Fetching user details based on userId
        User userDetails = userRepository.findById(userId).get();
        //Fetching user rating details based on userId
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        //collect all hotel ids in rating details
        List<String> hotelIdsList = ratings.stream().map(rating -> rating.getHotelId()).toList();
        //Fetching Hotel details based on hotel ids
        List<Hotel> hotelDetails = hotelService.getHotelDetailsByHotelIds(hotelIdsList);
        Map<String, Hotel> hotelIdAndHotelMap = hotelDetails.stream()
                .collect(Collectors.toMap(Hotel::getId, hotelData -> hotelData));
        ratings.forEach(rating -> rating.setHotel(hotelIdAndHotelMap.get(rating.getHotelId())));
        userDetails.setRatings(ratings);
        return userDetails;
    }

    public String updateUser(User user) {
        userRepository.save(user);
        return "User updated successfully";
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }


}


