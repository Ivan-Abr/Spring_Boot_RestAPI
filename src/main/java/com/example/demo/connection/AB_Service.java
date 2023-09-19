package com.example.demo.connection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AB_Service {
    private final AB_Repository ab_repository;

    @Autowired
    public AB_Service(AB_Repository abRepository) {
        this.ab_repository = abRepository;
    }
    
    public List<Author_Book> getConnections(){return ab_repository.findAll();}
    
}
