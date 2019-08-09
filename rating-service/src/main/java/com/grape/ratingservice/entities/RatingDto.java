package com.grape.ratingservice.entities;

public class RatingDto {
    public float rate;

    public RatingDto(Rating rating) {
        this.rate = rating.getRate();
    }
}
