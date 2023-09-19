package com.example.demo.authors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AuthorConfig {
    @Bean
    CommandLineRunner commandLineRun(AuthorRepository repository){
        return args -> {
            Author pushkin = new Author(
                1L,
                "Pushkin",
                "Pushkin@mail.ru",
                    LocalDate.of(1799, Month.JUNE, 6)
            );

            Author tolstoy = new Author(
                    2L,
                    "Tolstoy",
                    "Tolstoy@mail.ru",
                    LocalDate.of(1000, Month.APRIL, 3)
            );
            repository.saveAll(
                    List.of(pushkin, tolstoy)
            );
        };
    }
}
