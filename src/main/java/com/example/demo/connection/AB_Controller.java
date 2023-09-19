package com.example.demo.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/connection")
public class AB_Controller
{
    private final AB_Service ab_service;

    @Autowired
    public AB_Controller(AB_Service abService) {
        this.ab_service = abService;
    }

    @GetMapping
    public List<Author_Book> getCons(){return ab_service.getConnections();}
}
