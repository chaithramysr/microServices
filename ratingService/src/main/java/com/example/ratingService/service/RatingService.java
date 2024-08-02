package com.example.ratingService.service;

import com.example.ratingService.entity.Rating;
import com.example.ratingService.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public String addRating(Rating rating) {
        ratingRepository.save(rating);
        return "Rating added successfully";
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingByRatingId(String ratingId) {
        return ratingRepository.findByRatingId(ratingId);
    }

    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    public String updateRating(Rating rating) {
        ratingRepository.save(rating);
        return "Rating updated successfully";
    }

    public String deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
        return "Rating deleted successfully";
    }
}
