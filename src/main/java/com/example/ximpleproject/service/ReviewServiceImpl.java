package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.ReviewDto;
import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.entity.Review;
import com.example.ximpleproject.entity.User;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.repository.BookRepository;
import com.example.ximpleproject.repository.ReviewRepository;
import com.example.ximpleproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @CachePut(value = "reviews")
    public ReviewDto rateBook(ReviewDto dto) throws BookNotFoundException, UserNotFoundException {
        Review review = modelMapper.map(dto, Review.class);

        Book book  =  Optional.of(bookRepository.findById(review.getBook().getId())).get().orElseThrow(BookNotFoundException::new);
        User user = Optional.of(userRepository.findById(review.getUser().getId())).get().orElseThrow(UserNotFoundException::new);

        review.setBook(book);
        review.setUser(user);

        review = reviewRepository.saveAndFlush(review);
        return modelMapper.map(review, ReviewDto.class);
    }

    @Cacheable("reviews")
    public List<ReviewDto> getAllReviews(){
        return reviewRepository.findAll().stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
    }

}
