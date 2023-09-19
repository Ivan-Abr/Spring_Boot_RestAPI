package com.example.demo.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;




import org.hamcrest.CoreMatchers;


import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;



import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private  Book book;

    @BeforeEach
    public void init(){
        book = new Book(
                1L,
                "Buratino",
                "3 Krota",
                LocalDate.of(1987, Month.FEBRUARY, 13),
                "hoaDHAOHDOAHD"
        );
    }
    @Test
    public void postBook() throws Exception{
        given(bookService.addNewBook(ArgumentMatchers.any())).willAnswer((invocation->invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(book.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publisher", CoreMatchers.is(book.getPublisher())));
    }

    @Test
    public void getBooks() throws  Exception{
        Book book1 = book;
        when(bookService.getBooks()).thenReturn((List<Book>) book1);
        ResultActions response = mockMvc.perform(get("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void getBookById() throws Exception{
        long book_id = 1L;
        when(bookService.getBookById(book_id)).thenReturn(Optional.ofNullable((book)));
        ResultActions response = mockMvc.perform(get("/api/v1/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    // @Test
//    public void updateBook() throws Exception {
//        long book_id = 1L;
//        when(bookService.updateBook(book_id,"name","annot")).thenReturn(book);
//    }

    @Test
    public void deleteBook() throws Exception {
        long book_id = 1L;
        doNothing().when(bookService).deleteBook(1L);
        doNothing().when(bookService).deleteAssign(1L);
        ResultActions response = mockMvc.perform(delete("/api/v1/book/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}