package com.example.demo.books;


import com.example.demo.authors.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository
    extends JpaRepository<Book, Long>{
    @Query("select b from Book b where b.name = ?1")
    Optional<Book> findBookByName(String name);


    @Query("SELECT b FROM Book b\n " +
            "INNER JOIN Author_Book ab ON ab.book_id =  b.book_id\n" +
            "INNER JOIN Author a ON a.author_id = ab.author_id\n" +
            "WHERE a.author_id = 1\n")
    List<Book> findBookByAuthor_id(Long AuthorId);

}

