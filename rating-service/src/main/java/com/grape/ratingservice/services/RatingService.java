package com.grape.ratingservice.services;

import com.grape.ratingservice.entities.Rating;
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

    //rate
    //((Overall Rating * Total Rating) + new Rating) / (Total Rating + 1)
}
