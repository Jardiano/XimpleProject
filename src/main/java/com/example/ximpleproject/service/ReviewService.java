package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto rateBook(ReviewDto dto);
    List<ReviewDto> getAllReviews();
}
