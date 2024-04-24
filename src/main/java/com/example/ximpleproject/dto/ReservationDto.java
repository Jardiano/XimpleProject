package com.example.ximpleproject.dto;

import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {

    private Long id;
    private BookDto book;
    private UserDto user;
    private Date startDate;
    private Date endDate;

}
