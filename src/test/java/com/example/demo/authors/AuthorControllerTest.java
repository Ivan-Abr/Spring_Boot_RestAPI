package com.example.demo.authors;

import com.example.demo.authors.Author;
import com.example.demo.authors.AuthorService;
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

@WebMvcTest(controllers = AuthorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author;

    @BeforeEach
    public void init(){
        author = new Author(
                7L,
                "Kolotushkin",
                "Pushkin@mail.ru",
                LocalDate.of(1799, Month.JUNE, 6)
        );
    }
    //@Test
//    public void postAuthor() throws Exception{
//        given(authorService.addNewAuthor(ArgumentMatchers.any())).willAnswer((invocation->invocation.getArgument(0)));
//
//        ResultActions response = mockMvc.perform(post("/api/v1/author")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(author)));
//
//        response.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(author.getAuthor_name())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.publisher", CoreMatchers.is(author.getEmail())));
//    }

    @Test
    public void getAuthors() throws  Exception{
        Author author1 = author;
        when(authorService.getAuthors()).thenReturn((List<Author>) author1);
        ResultActions response = mockMvc.perform(get("/api/v1/author")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void getAuthorById() throws Exception{
        long book_id = 1L;
        when(authorService.getAuthorById(book_id)).thenReturn(Optional.ofNullable((author)));
        ResultActions response = mockMvc.perform(get("/api/v1/author/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    // @Test
//    public void updateBook() throws Exception {
//        long book_id = 1L;
//        when(bookService.updateBook(book_id,"name","annot")).thenReturn(book);
//    }

    @Test
    public void deleteAuthor() throws Exception {

        doNothing().when(authorService).deleteAuthor(3L);
        ResultActions response = mockMvc.perform(delete("/api/v1/author/1")
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}