package com.example.userService.externalServices;

import com.example.userService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/getRatingByUserId/{userId}")
    List<Rating> getRatingByUserId(@PathVariable String userId);
}
