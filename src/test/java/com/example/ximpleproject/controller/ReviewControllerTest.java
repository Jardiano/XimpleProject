package com.example.ximpleproject.controller;

import com.example.ximpleproject.dto.BookDto;
import com.example.ximpleproject.dto.ReviewDto;
import com.example.ximpleproject.dto.UserDto;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.service.ReservationService;
import com.example.ximpleproject.service.ReviewService;
import com.example.ximpleproject.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ReviewControllerTest {

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRateBook() throws Exception {
        String jsonResponse = "{\"id\":3,\"user\":{\"id\":1,\"name\":\"UserTest1\",\"email\":\"email1@test.com\"},\"book\":{\"id\":2,\"title\":\"Need for speed\",\"genre\":\"action\",\"resume\":\"book about a car race\",\"reserved\":false},\"text\":\"Amazing story\",\"rating\":4}";
        ReviewDto responseDto = new ObjectMapper().readValue(jsonResponse, ReviewDto.class);

        ReviewDto dto = ReviewDto.builder()
                .book(BookDto.builder().id(2L).build())
                .user(UserDto.builder().id(1L).build())
                .rating(4)
                .text("Amazing story")
                .build();

        Mockito.when(reviewService.rateBook(Mockito.any(ReviewDto.class))).thenReturn(responseDto);

        this.mockMvc.perform(post("/v1/review")
                        .content(Utils.asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(3)))
                .andExpect(jsonPath("$.book.id", equalTo(2)))
                .andExpect(jsonPath("$.user.id", equalTo(1)));



    }

    @Test
    void testRateBookWhenBookNotExist() throws Exception {
        String jsonResponse = "{\"id\":3,\"user\":{\"id\":1,\"name\":\"UserTest1\",\"email\":\"email1@test.com\"},\"book\":{\"id\":2,\"title\":\"Need for speed\",\"genre\":\"action\",\"resume\":\"book about a car race\",\"reserved\":false},\"text\":\"Amazing story\",\"rating\":4}";
        ReviewDto responseDto = new ObjectMapper().readValue(jsonResponse, ReviewDto.class);

        ReviewDto dto = ReviewDto.builder()
                .book(BookDto.builder().id(2L).build())
                .user(UserDto.builder().id(1L).build())
                .rating(4)
                .text("Amazing story")
                .build();

        Mockito.when(reviewService.rateBook(Mockito.any(ReviewDto.class))).thenThrow(new BookNotFoundException());

        this.mockMvc.perform(post("/v1/review")
                        .content(Utils.asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRateBookWhenUserNotExist() throws Exception {
        String jsonResponse = "{\"id\":3,\"user\":{\"id\":1,\"name\":\"UserTest1\",\"email\":\"email1@test.com\"},\"book\":{\"id\":2,\"title\":\"Need for speed\",\"genre\":\"action\",\"resume\":\"book about a car race\",\"reserved\":false},\"text\":\"Amazing story\",\"rating\":4}";
        ReviewDto responseDto = new ObjectMapper().readValue(jsonResponse, ReviewDto.class);

        ReviewDto dto = ReviewDto.builder()
                .book(BookDto.builder().id(2L).build())
                .user(UserDto.builder().id(1L).build())
                .rating(4)
                .text("Amazing story")
                .build();

        Mockito.when(reviewService.rateBook(Mockito.any(ReviewDto.class))).thenThrow(new UserNotFoundException());

        this.mockMvc.perform(post("/v1/review")
                        .content(Utils.asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllReviews() throws Exception {
        List<ReviewDto> reviewList = new ArrayList<>();
        reviewList.add(ReviewDto.builder().build());
        reviewList.add(ReviewDto.builder().build());
        reviewList.add(ReviewDto.builder().build());

        Mockito.when(reviewService.getAllReviews()).thenReturn(reviewList);

        this.mockMvc.perform(get("/v1/review")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }
}