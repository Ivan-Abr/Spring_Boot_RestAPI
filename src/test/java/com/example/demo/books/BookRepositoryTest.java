package com.example.demo.books;


import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    public void Book_Repo_SaveTest(){

        Book book = new Book(
                3L,
                "Arbuz",
                "West",
                LocalDate.of(1987, Month.FEBRUARY, 13),
                "Neploho"
        );
        Book savedBook = bookRepository.save(book);
        Assertions.assertNotNull(savedBook);
    }

}
