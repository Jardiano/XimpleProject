package com.example.ximpleproject.controller;

import com.example.ximpleproject.dto.BookDto;
import com.example.ximpleproject.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @Operation(summary = "List all books registered")
    @GetMapping
    public ResponseEntity<List<BookDto>> getBooksList(){
        log.info("Got a request");
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @Operation(summary = "List only books available to be reserved")
    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getBooksListAvailable(){
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }

}
