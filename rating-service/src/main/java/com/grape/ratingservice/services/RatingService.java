package com.grape.ratingservice.services;

import com.grape.ratingservice.entities.Rating;
import com.grape.ratingservice.entities.RatingDto;
import com.grape.ratingservice.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public List<Rating> getRatings() {
        return repository.findAll();
    }

    public Optional<Rating> getRatingBy(Long roomId) {
        return repository.findByRoomId(roomId);
    }

    //for test purposes
    public Rating save(Rating rating) {
        return repository.save(rating);
    }

    //rate
    //((Overall Rating * Total Rating) + new Rating) / (Total Rating + 1)
}
