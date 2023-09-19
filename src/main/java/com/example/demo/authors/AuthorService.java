package com.example.demo.authors;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository)
    {this.authorRepository = authorRepository;}

    public List<Author> getAuthors(){return authorRepository.findAll();}
    public Optional<Author> getAuthorById(Long AuthorId){
        boolean exists = authorRepository.existsById(AuthorId);
        if (!exists){
            throw new IllegalStateException("Author with id" + AuthorId + "does not exist");
        }
         return authorRepository.findById(AuthorId);
    }

    public boolean addNewAuthor(Author author){
        Optional<Author> authorOptional = authorRepository
                .findAuthorByEmail(author.getEmail());
        if (authorOptional.isPresent()){
            throw new IllegalStateException("email.taken");
        }
        authorRepository.save(author);

        return true;
    }

    public void deleteAuthor(Long AuthorId){
        boolean exists = authorRepository.existsById(AuthorId);
        if (!exists){
            throw new IllegalStateException("Student with id" + AuthorId + "does not exist");
        }
        authorRepository.deleteById(AuthorId);
    }

    @Transactional
    public void updateAuthor(Long AuthorId,
                             String name,
                             String email){
        Author author = authorRepository.findById(AuthorId)
                .orElseThrow(()-> new IllegalStateException("Student with id" + AuthorId + "does not exist"));
        if (name!=null &&
            name.length() > 0 &&
            !Objects.equals(author.getAuthor_name(), name)){
            author.setAuthor_name(name);
        }

        if (email!=null &&
        email.length() > 0 &&
        !Objects.equals(author.getEmail(), email)){
            Optional<Author> authorOptional = authorRepository
                    .findAuthorByEmail(email);
            if (authorOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            author.setEmail(email);
        }
    }

}
