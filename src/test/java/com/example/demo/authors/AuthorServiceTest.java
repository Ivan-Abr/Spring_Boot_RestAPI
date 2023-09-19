package com.example.demo.authors;


import com.example.demo.books.Book;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AbstractSoftAssertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    public void setup(){
        author = new Author(
                2L,
                "Tolstoy",
                "Tolstoy@mail.ru",
                LocalDate.of(1000, Month.APRIL, 3));
    }


    @Test
    @Transactional
    public void AddNewAuthor(){
        Author author = new Author();
        author.setAuthor_id(6L);
        Assert.assertTrue(authorService.addNewAuthor(author));

    }

    @Test
    @Transactional
    public void getAllAuthors()
    {
        Assert.assertNotNull(authorService.getAuthors());
    }

    @Test
    public void getAuthorById(){
        Author test_aut = new Author(
                4L,
                "Kushkin",
                "Kushkin@mail.ru",
                LocalDate.of(1800, Month.JUNE, 16)
        );
        when(authorRepository.findById(any())).thenReturn(Optional.ofNullable(test_aut));
        when(authorRepository.existsById(any())).thenReturn(true);
        Optional<Author> author1 = authorService.getAuthorById(4L);
        //Assert.assertNotNull(test_book);
        Assert.assertEquals(author1.get().getAuthor_name(), "Kushkin");
        Assert.assertEquals(author1.get().getEmail(), "Kushkin@mail.ru");
    }

    @Test
    public void updateAuthor(){
        Author author_test = new Author(
                4L,
                "Kushkin",
                "Kushkin@mail.ru",
                LocalDate.of(1800, Month.JUNE, 16)
        );
        when(authorRepository.findById(any())).thenReturn(Optional.ofNullable(author_test));
        when(authorRepository.existsById(any())).thenReturn(true);
        String at_test_name = "Tushkin";
        String at_test_email = "tushka@mail.ru";
        authorService.updateAuthor(4L, at_test_name, at_test_email);
        Assert.assertEquals(author_test.getAuthor_name(), at_test_name);
        Assert.assertEquals(author_test.getEmail(), at_test_email);
    }


    @Test
    public void deleteAuthor(){
        long author_id = 4L;
        Author author_test = new Author(
                author_id,
                "Kushkin",
                "Kushkin@mail.ru",
                LocalDate.of(1800, Month.JUNE, 16));
        when(authorRepository.findById(any())).thenReturn(Optional.ofNullable(author_test));
        when(authorRepository.save(author_test)).thenReturn(author_test);
        when(authorRepository.existsById(any())).thenReturn(true);
        doNothing().when(authorRepository).delete(author_test);
        Assertions.assertAll(() -> authorService.deleteAuthor(author_id));
    }
}

