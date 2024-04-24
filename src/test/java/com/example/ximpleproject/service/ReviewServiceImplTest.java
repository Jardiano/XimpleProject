package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.BookDto;
import com.example.ximpleproject.dto.ReviewDto;
import com.example.ximpleproject.dto.UserDto;
import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.entity.Review;
import com.example.ximpleproject.entity.User;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.repository.BookRepository;
import com.example.ximpleproject.repository.ReviewRepository;
import com.example.ximpleproject.repository.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
class ReviewServiceImplTest {

    private ReviewServiceImpl reviewService;
    @MockBean
    private ReviewRepository reviewRepository;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private Review review;
    private ReviewDto dto;
    private Book book;
    private User user;

    @BeforeEach
    public void setUp(){
        modelMapper = new ModelMapper();
        review = Review.builder().build();
        dto = ReviewDto.builder()
                .book(BookDto.builder().id(1L).build())
                .user(UserDto.builder().id(2L).build())
                .build();

        book = Book.builder().build();
        user = User.builder().build();
        Mockito.when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Mockito.when(reviewRepository.saveAndFlush(Mockito.any(Review.class))).thenReturn(review);

        reviewService = new ReviewServiceImpl(reviewRepository,bookRepository, userRepository, modelMapper);
    }

    @Test
    void testRateBook() {
        ReviewDto reviewDto = reviewService.rateBook(dto);
        assertNotNull(reviewDto);
    }

    @Test
    void testRateBookWhenBookNotExist() {
        Mockito.when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> reviewService.rateBook(dto));
    }

    @Test
    void testRateBookWhenUserNotExist() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> reviewService.rateBook(dto));
    }
}