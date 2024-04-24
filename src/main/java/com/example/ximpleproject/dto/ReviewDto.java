package com.example.ximpleproject.dto;

import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long id;
    private UserDto user;
    private BookDto book;
    private String text;
    private Integer rating;

}
