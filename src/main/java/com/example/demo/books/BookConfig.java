package com.example.demo.books;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
     return args -> {
         Book buratino = new Book(
                 1L,
                 "Buratino",
                 "3 Krota",
                 LocalDate.of(1987, Month.FEBRUARY, 13),
                 "hoaDHAOHDOAHD"
         );
         Book golden_fish = new Book(
                 2L,
                 "Golden Fish",
                 "MosPechat",
                 LocalDate.of(1987, Month.FEBRUARY, 13),
                 "QIJASFAFJLA"
         );
        repository.saveAll(
                List.of(buratino, golden_fish)
        );
     }  ;
    }
}
