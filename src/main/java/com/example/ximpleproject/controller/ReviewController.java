package com.example.ximpleproject.controller;

import com.example.ximpleproject.dto.ReviewDto;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Rate the book",
            description = "Register the review of the book.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDto> rateBook(@Valid @RequestBody ReviewDto dto)  throws UserNotFoundException, BookNotFoundException {
        return ResponseEntity.ok(reviewService.rateBook(dto));
    }
    @Operation(summary = "List all the reviews registered")
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }


}
