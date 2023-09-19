package com.example.demo.authors;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository
    extends JpaRepository<Author, Long>{
        @Query("SELECT a from Author a Where a.email = ?1")
        Optional<Author> findAuthorByEmail(String email);

        @Query("SELECT a from Author a WHERE a.author_id =?1")
        Optional<Author> findAuthorByAuthor_id(Long author_id);

        @Query("SELECT CASE WHEN COUNT(a) > 0 THEN " +
                "TRUE ELSE FALSE END " +
                "FROM Author a WHERE a.email = ?1")
    Boolean selectExistsEmail(String email);
    }


