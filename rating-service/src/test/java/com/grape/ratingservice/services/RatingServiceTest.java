package com.grape.ratingservice.services;

import com.grape.ratingservice.entities.Rating;
import com.grape.ratingservice.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.internal.util.JavaEightUtil.emptyOptional;

@ExtendWith(SpringExtension.class)
class RatingServiceTest {

    @Mock
    RatingRepository repository;

    @InjectMocks
    RatingService service;

    @Test
    void givenNoRatingsReturnEmptyList() {

        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Rating> expectedRatings = service.getRatings();

        assertThat(expectedRatings.isEmpty(), is(true));

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnAllRatings() {

        List<Rating> mockedRatings = getMockedRatings();
        when(repository.findAll()).thenReturn(mockedRatings);

        List<Rating> expectedRatings = service.getRatings();

        assertThat(expectedRatings, hasSize(mockedRatings.size()));

        verify(repository, times(1)).findAll();
    }

    @Test
    void givenRoomIdReturnRating() {
        Long roomId = 44L;
        float roomRate = 5f;
        Rating rating = new Rating(roomId, 111L, roomRate);

        when(repository.findByRoomId(roomId)).thenReturn(Optional.of(rating));

        Optional<Rating> roomRating = service.getRatingBy(roomId);

        assertThat(roomRating.get(), equalTo(rating));

    }

    @Test
    void givenNotExistingRoomReturnEmpty() {
        when(repository.findByRoomId(anyLong())).thenReturn(Optional.empty());

        Optional<Rating> roomRating = service.getRatingBy(123L);

        assertThat(roomRating, is(emptyOptional()));
    }

    private List<Rating> getMockedRatings() {
        return Arrays.asList(
                new Rating(123L, 222L, 2),
                new Rating(457L, 28L, 3),
                new Rating(123L, 32L, 5)
        );
    }
}