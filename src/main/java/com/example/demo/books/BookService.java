package com.example.demo.books;


import com.example.demo.authors.Author;
import com.example.demo.authors.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


@Getter
@Service
public class BookService {
   private final BookRepository bookRepository;

   private  final AuthorRepository authorRepository;

   @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){this.bookRepository = bookRepository;
       this.authorRepository = authorRepository;
   }



    public List<Book> getBooks(){return bookRepository.findAll();}

    public Optional<Book> getBookById(Long BookId)
    {
        boolean exists = bookRepository.existsById(BookId);
        if (!exists){
            throw new IllegalStateException("Book with id" + BookId + "does not exist");
        }
        return bookRepository.findById(BookId);}
    public Book addNewBook(Book book){
        Optional<Book> bookOptional = bookRepository
                .findBookByName(book.getName());
        if (bookOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }

        return bookRepository.save(book);
    }

    public void deleteBook(Long BookId){
       boolean exists = bookRepository.existsById(BookId);
       if (!exists){
           throw new IllegalStateException("student with id" + BookId + "does not exist");
       }
       bookRepository.deleteById(BookId);

    }

    @Transactional
    public boolean updateBook(Long BookId, String name, String annotation){
       Book book = bookRepository.findById(BookId)
               .orElseThrow(()-> new IllegalStateException("student with id" + BookId + "does not exist"));
       if (name!= null && name.length()>0 && !Objects.equals(book.getName(), name)){
           book.setName(name);
       }
           book.setAnnotation(annotation);
       return true;
    }

    public List<Book> getBookByAuthorId(Long AuthorId)
    {
        boolean exists = authorRepository.existsById(AuthorId);
        if (!exists){
            throw new IllegalStateException("Author with id" + AuthorId+ "does not exist");
        }
        return bookRepository.findBookByAuthor_id(AuthorId);}



    //many-to-many connection
    public Book assignAuthorToBook(Long BookId, Long AuthorId) {
       Set<Author> authors = null;
       Book book = bookRepository.findById(BookId).get();
        Author author = authorRepository.findById(AuthorId).get();
        authors = book.getAuthors();
        authors.add(author);
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    public void deleteAssign(Long BookId){
        //Set<Author> authors = null;
       Book book = bookRepository.findById(BookId).get();
       book.setAuthors(null);
       bookRepository.save(book);
    }
}
