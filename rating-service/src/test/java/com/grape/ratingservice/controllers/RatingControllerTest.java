package com.grape.ratingservice.controllers;

import com.grape.ratingservice.entities.Rating;
import com.grape.ratingservice.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService service;

    @Test
    void shouldReturnAllRatings() throws Exception {

        when(service.getRatings()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/ratings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        verify(service, times(1)).getRatings();
    }

    @Test
    void shouldReturnRatingForRoom() throws Exception {
        Long roomId = 123L;
        float rate = 4;
        when(service.getRatingBy(roomId)).thenReturn(Optional.of(new Rating(roomId, 22L, rate)));

        mockMvc.perform(get("/rating/" + roomId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rate").value(rate))
                .andReturn();

        verify(service, times(1)).getRatingBy(roomId);
    }

    @Test
    void shouldReturnNotFoundForNonExistingRoom() throws Exception {
        Long roomId = 123L;
        when(service.getRatingBy(roomId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/rating/" + roomId))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(service, times(1)).getRatingBy(roomId);
    }


}