package com.example.ratingService.repository;

import com.example.ratingService.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByRatingId(String ratingId);

    List<Rating> findByUserId(String userId);
}
