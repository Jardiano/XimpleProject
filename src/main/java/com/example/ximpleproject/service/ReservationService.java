package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.ReservationDto;


public interface ReservationService {

    boolean makeReservation(ReservationDto dto);

    boolean makeReservation(Long bookId, Long userId);
}
