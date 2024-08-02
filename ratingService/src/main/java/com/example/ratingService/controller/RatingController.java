package com.example.ratingService.controller;

import com.example.ratingService.entity.Rating;
import com.example.ratingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/addRating")
    public String addRating(@RequestBody Rating rating){
        return ratingService.addRating(rating);
    }

    @GetMapping("/getAllRatings")
    public List<Rating> getAllRatings(){
        return ratingService.getAllRatings();
    }

    @GetMapping("/getRatingByRatingId/{id}")
    public List<Rating> getRatingByRatingId(@PathVariable("id") String ratingId){
        return ratingService.getRatingByRatingId(ratingId);
    }

    @GetMapping("/getRatingByUserId/{id}")
    public List<Rating> getRatingByUserId(@PathVariable("id") String userId){
        return ratingService.getRatingByUserId(userId);
    }

    @PostMapping("/updateRating")
    public String updateRating(@RequestBody Rating rating){
        return ratingService.updateRating(rating);
    }

    @DeleteMapping("/deleteRating/{id}")
    public String deleteRating(@PathVariable("id") String ratingId){
        return ratingService.deleteRating(ratingId);
    }
}
