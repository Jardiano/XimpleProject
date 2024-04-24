package com.example.ximpleproject.service;

import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.entity.User;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.repository.BookRepository;
import com.example.ximpleproject.repository.ReservationRepository;
import com.example.ximpleproject.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
class ReservationServiceImplTest {
    @Autowired
    private ReservationServiceImpl reservationService;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private ReservationRepository reservationRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ModelMapper modelMapper;

    private Book book;
    private User user;


    @BeforeEach
    public void setUp(){
        book = Book.builder().build();
        user = User.builder().build();
        Mockito.when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
    }
    @Test
    void testMakeReservationDtoWhenReservationIsConfirmed() {
        Assert.assertTrue(reservationService.makeReservation(1L, 1L));
    }

    @Test
    void testMakeReservationDtoWhenBookNotExist() {
        Mockito.when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> reservationService.makeReservation(1L, 1L));
    }

    @Test
    void testMakeReservationDtoWhenUserNotExist() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> reservationService.makeReservation(1L, 1L));
    }





}