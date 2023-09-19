package com.example.demo.books;
import com.example.demo.authors.Author;
import com.example.demo.authors.AuthorRepository;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString

@Table(name = "book")
public class Book  {


    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Id
    private Long book_id;

    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private Set<Author> authors = new HashSet<>();



    private String name;
    private String publisher;
    private LocalDate dop;
    private String annotation;

    public Book()
    {

    }

    public Book(Long book_id, String book_name,
                String publisher,
                LocalDate dop, String annotation) {
        this.book_id = book_id;
        this.name = book_name;
        this.publisher = publisher;
        this.dop = dop;
        this.annotation = annotation;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + book_id +
//                ", name='" + name + '\'' +
//                ", publisher='" + publisher + '\'' +
//                ", dop=" + dop +
//                ",annotation=" + annotation +
//                '}';
//    }
}
