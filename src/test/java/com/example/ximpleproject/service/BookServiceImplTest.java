package com.example.ximpleproject.service;

import com.example.ximpleproject.dto.BookDto;
import com.example.ximpleproject.entity.Book;
import com.example.ximpleproject.repository.BookRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;
    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void testGetAllBooks() {
        List<Book> bookList  = new ArrayList<>();
        bookList.add(Book.builder().id(1L).build());
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);

        List<BookDto> allBooks = bookService.getAllBooks();

        Assert.assertEquals(allBooks.size(), 1);

    }

    @Test
    void testGetAvailableBooks() {
        List<Book> bookList  = new ArrayList<>();
        bookList.add(Book.builder().id(1L).reserved(false).build());
        bookList.add(Book.builder().id(2L).reserved(true).build());
        bookList.add(Book.builder().id(3L).reserved(false).build());
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);

        List<BookDto> availableBooks = bookService.getAvailableBooks();

        Assert.assertEquals(availableBooks.size(), 2);

    }
}