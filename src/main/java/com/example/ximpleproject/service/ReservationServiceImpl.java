package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.ReservationDto;
import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.entity.Reservation;
import com.example.ximpleproject.entity.User;
import com.example.ximpleproject.exception.BookNotFoundException;
import com.example.ximpleproject.exception.UserNotFoundException;
import com.example.ximpleproject.repository.BookRepository;
import com.example.ximpleproject.repository.ReservationRepository;
import com.example.ximpleproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private BookRepository bookRepository;
    private ReservationRepository reservationRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(BookRepository bookRepository, ReservationRepository reservationRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public boolean makeReservation(ReservationDto dto){

        if(dto.getBook().isReserved()){
            LOG.info("Book" + dto.getBook().getId()+"already reserved");
            return false;
        }
        dto.setStartDate(new Date());
        Reservation reservation = modelMapper.map(dto, Reservation.class);
        reservationRepository.save(reservation);

        return true;

    }
    @CacheEvict(value = "books", allEntries=true )
    public boolean makeReservation(Long bookId, Long userId) throws BookNotFoundException, UserNotFoundException {

        Book book  = Optional.of(bookRepository.findById(bookId)).get().orElseThrow(BookNotFoundException::new);
        User user = Optional.of(userRepository.findById(userId)).get().orElseThrow(UserNotFoundException::new);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setStartDate(new Date());

        reservationRepository.save(reservation);

        LOG.info("Reservation" + reservation.getId() +"confirmed for book" + reservation.getBook().getId());
        return true;
    }

    }
