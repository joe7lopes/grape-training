package com.grape.ratingservice.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public final class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingId;
    private Long roomId;
    private Long userId;
    private float rate;

    public Rating() {
    }

    public Rating(Long roomId, Long userId, float rate) {
        this.roomId = roomId;
        this.userId = userId;
        this.rate = rate;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Float.compare(rating.rate, rate) == 0 &&
                Objects.equals(ratingId, rating.ratingId) &&
                Objects.equals(roomId, rating.roomId) &&
                Objects.equals(userId, rating.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, roomId, userId, rate);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", rate=" + rate +
                '}';
    }
}
