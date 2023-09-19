package com.example.demo.connection;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "authors_books")
public class Author_Book {
    @Id
    private Long book_id;
    private Long author_id;

    public Author_Book() {

    }
    public Author_Book(Long book_id, Long author_id){
        this.book_id = book_id;
        this.author_id = author_id;
    }


}
