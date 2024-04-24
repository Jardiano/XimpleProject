package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.BookDto;
import com.example.ximpleproject.repository.BookRepository;
import io.micrometer.observation.annotation.Observed;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
    private BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("books")
    public List<BookDto> getAvailableBooks(){
        return bookRepository.findAll().stream()
                .filter(book -> !book.isReserved())
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());

    }
}
