package com.example.demo.books;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getBooks(){return bookService.getBooks();}

    @GetMapping(path = {"{BookId}"})
    public Optional<Book> getBookById(@PathVariable("BookId") Long BookId)
    {return bookService.getBookById(BookId);}

    @GetMapping(path = "{AuthorId}")
    public List<Book> getBookByAuthorId(@PathVariable("AuthorId") Long AuthorId)
    {
        return bookService.getBookByAuthorId(AuthorId);
    }

    @PostMapping
    public void registerNewBook(@RequestBody Book book){bookService.addNewBook(book);}

    @DeleteMapping(path = "{BookId}")
    public void deleteBook(@PathVariable("BookId") Long BookId){
        bookService.deleteAssign(BookId);
        bookService.deleteBook(BookId);
    }

    @PutMapping(path = "{BookId}")
    public void updateStudent(
            @PathVariable("BookId") Long BookId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String annotation)
    {bookService.updateBook(BookId, name, annotation);}

    @PutMapping(path = "{BookId}/author/{AuthorId}")
    public Book assignAuthorToBook(
            @PathVariable Long BookId,
            @PathVariable Long AuthorId
    ){
        return bookService.assignAuthorToBook(BookId, AuthorId);
    }

//    @DeleteMapping(path = "{BookId}/author")
//    public void deleteAssign(
//            @PathVariable Long BookId
//    ) {
//        bookService.deleteAssign(BookId);
//    }

}
