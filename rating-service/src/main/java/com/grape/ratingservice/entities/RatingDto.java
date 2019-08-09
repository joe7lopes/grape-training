package com.grape.ratingservice.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingDto {
    public float rate;
    public Long roomId;
    public Long userId;

    public RatingDto() {
    }

    public RatingDto(Rating rating) {
        this.rate = rating.getRate();
        this.roomId = rating.getRoomId();
    }
}
