package com.assessment.demoauthor.controller;

public class AuthorNotFoundException extends RuntimeException {

    AuthorNotFoundException(Integer id){
        super("could not find author" +id);
    }
    
}

