package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    List<BookDto> getAvailableBooks();
}
