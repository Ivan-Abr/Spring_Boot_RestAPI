package com.example.demo.connection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AB_Repository
extends JpaRepository<Author_Book, Long> {

}
