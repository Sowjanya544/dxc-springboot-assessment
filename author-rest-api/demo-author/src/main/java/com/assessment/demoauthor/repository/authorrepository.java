package com.assessment.demoauthor.repository;

import com.assessment.demoauthor.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer>{
    
}

