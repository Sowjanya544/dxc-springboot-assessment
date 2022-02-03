package com.assessment.demoauthor.services;

import java.util.List;

import com.assessment.demoauthor.model.author;

public interface authorservice {

    public author getAuthorById(int id);

    public author createAuthor(author author);

    public author updateauthor(author author);

    public void deleteauthor(int id);

    public List<author> findAllauthor();
    
}

