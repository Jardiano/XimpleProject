package com.example.ximpleproject.controller;

import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.service.ReservationService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ReservationControllerTest {

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMakeReservation() throws Exception {
        Mockito.when(reservationService.makeReservation(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);

        this.mockMvc.perform(post("/v1/reservations")
                        .param("bookId", "2")
                        .param("userId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Reservation made")));
    }

    @Test
    void testMakeReservationWhenBookIsAlreadyReserved() throws Exception {

        Mockito.when(reservationService.makeReservation(Mockito.anyLong(), Mockito.anyLong())).thenReturn(false);

        this.mockMvc.perform(post("/v1/reservations")
                        .param("bookId", "1")
                        .param("userId", "1"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(equalTo("Reservation not made, due to the book already being reserved by another user")));
    }

    @Test
    void testMakeReservationWhenBookNotExit() throws Exception {

        Mockito.when(reservationService.makeReservation(Mockito.anyLong(), Mockito.anyLong())).thenThrow(new BookNotFoundException());

        this.mockMvc.perform(post("/v1/reservations")
                        .param("bookId", "5")
                        .param("userId", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMakeReservationWhenUserNotExit() throws Exception {

        Mockito.when(reservationService.makeReservation(Mockito.anyLong(), Mockito.anyLong())).thenThrow(new UserNotFoundException());

        this.mockMvc.perform(post("/v1/reservations")
                        .param("bookId", "1")
                        .param("userId", "5"))
                .andExpect(status().isBadRequest());
    }

}