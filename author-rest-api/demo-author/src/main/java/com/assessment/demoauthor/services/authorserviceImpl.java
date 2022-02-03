package com.assessment.demoauthor.services;

import java.util.List;

import com.assessment.demoauthor.model.author;
import com.assessment.demoauthor.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthorServiceImpl implements AuthorService{


    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

    @Override
    public List<Author> findAllauthor() {
        return authorRepository.findAll();
    }
    
}
