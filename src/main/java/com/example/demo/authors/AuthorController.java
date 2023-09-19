package com.example.demo.authors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/author")
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService)
    {this.authorService = authorService;}

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping(path = "{AuthorId}")
            public Optional<Author> getAuthorById(@PathVariable("AuthorId") Long AuthorId)
    {return authorService.getAuthorById(AuthorId);}

    @PostMapping
    public void registerNewAuthor(@RequestBody Author author)
    {authorService.addNewAuthor(author);}

    @DeleteMapping(path = "{AuthorId}")
    public void deleteAuthor(@PathVariable("AuthorId")Long AuthorId)
    {authorService.deleteAuthor(AuthorId);}

    @PutMapping(path = "{AuthorId}")
    public void updateAuthor(
            @PathVariable("AuthorId") Long AuthorId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email)
    {authorService.updateAuthor(AuthorId,name,email);}

}
