package com.grape.ratingservice.controllers;

import com.grape.ratingservice.entities.RatingDto;
import com.grape.ratingservice.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RatingController {

    private RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    @GetMapping("/ratings")
    public List<RatingDto> getAllRatings() {
        return service.getRatings().stream()
                .map(RatingDto::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/rating/{roomId}")
    public RatingDto getRatingBy(@PathVariable Long roomId) {
        return service.getRatingBy(roomId)
                .map(RatingDto::new)
                .orElseThrow(() -> new RoomNotFoundException(roomId));
    }

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String roomNotFoundExceptionHandler(Exception e) {
        return e.getMessage();
    }

    private class RoomNotFoundException extends RuntimeException {
        RoomNotFoundException(Long roomId) {
            super("Room " + roomId + " doesn't exist");
        }
    }

}
