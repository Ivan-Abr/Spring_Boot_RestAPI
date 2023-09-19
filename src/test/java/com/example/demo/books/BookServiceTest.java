package com.example.demo.books;

import com.example.demo.authors.Author;
import com.example.demo.authors.AuthorRepository;
import com.example.demo.authors.AuthorService;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit.jupiter.web.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;


    @InjectMocks
    private BookService bookService;

    @InjectMocks
    private AuthorService authorService;

    private Book book;
    private Author author;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setBook_id(5L);
        book.setName("Test book");
        book.setAuthors(null);
        book.setPublisher("?????");
        book.setDop(LocalDate.of(12,1,6));
        book.setAnnotation("@&^#b24029&#$07242049$#*j39jdsd0");

        author = new Author(
                1L,
                "Autor",
                "Autor@mail.ru",
                LocalDate.of(1000, Month.APRIL, 3));


    }


    @Test
    @Transactional
    void addNewBook() {

        when(bookRepository.save(book)).thenReturn(book);
        Book book1 = bookService.addNewBook(book);
        Assert.assertNotNull(book1);
    }
    @Test
    @Transactional
    void getAllBooks(){
        Assert.assertNotNull(bookService.getBooks());
    }

    @Test
    public void getBookById(){
        Book test_book = new Book(
                6L,
                "How2Flex",
                "Pablo",
                LocalDate.of(2013, Month.FEBRUARY, 1),
                "???????"
        );
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(test_book));
        when(bookRepository.existsById(any())).thenReturn(true);
        Optional<Book> book1 = bookService.getBookById(6L);
        //Assert.assertNotNull(test_book);
        Assert.assertEquals(book1.get().getName(), "How2Flex");
        Assert.assertEquals(book1.get().getPublisher(), "Pablo");
    }


//    @Test
//    void getBookById(){
//        long book_id = 5L;
//        Book book = new Book(
//                5L,
//                "Aboba",
//                "MosPechat",
//                LocalDate.of(1987, Month.FEBRUARY, 13),
//                "QIJASFAFJLA"
//        );
//        when(bookRepository.findById(book_id)).thenReturn(Optional.ofNullable(book));




//        System.out.println(book);
//        bookService.addNewBook(book);
//        //Assert.assertTrue(bookService.addNewBook(book));
//        //System.out.println(bookService.getBooks());
        //Optional<Book> bookReturn = bookService.getBookById(book_id);


        //System.out.println(bookReturn);


    @Test
    public void updateBook(){
        long book_id = book.getBook_id();
        when(bookRepository.findById(book_id)).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(book)).thenReturn(book);
        Assertions.assertTrue(bookService.updateBook(book_id, "Arbuz", "Norm"));
    }

    @Test
    public void DeleteBook(){
        long book_id = 3L;
        Book book = new Book(
                book_id,
                "Kniga",
                "akdakd",
                LocalDate.of(2013, Month.SEPTEMBER, 14),
                "QIJASFAFJLA"
        );
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(bookRepository.existsById(any())).thenReturn(true);
        doNothing().when(bookRepository).delete(book);

        assertAll(() -> bookService.deleteBook(book_id));
    }

//    @Test
//    public void assignation() {
//        Set<Author> authors = null;
//        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
//        //when(bookRepository.save(book)).thenReturn(book);
//        //when(bookRepository.existsById(any())).thenReturn(true);
//        when(authorRepository.existsById(any())).thenReturn(Optional.ofNullable(authors));
//    }

}