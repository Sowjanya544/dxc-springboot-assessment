package com.assessment.demoauthor.controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.assessment.demoauthor.services.AuthorService;
import com.assessment.demoauthor.dto.AuthorDto;
import com.assessment.demoauthor.model.Author;
import com.assessment.demoauthor.controller.AuthorNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org .springframework.validation.FieldError;




@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/author")
    public ResponseEntity<List<Author>> findAllauthor(){
        List<Author> authors=authorService.findAllauthor();
        return ResponseEntity.status(HttpStatus.OK).body(authors);

    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody AuthorDto authorReqObj){

        Author author = modelMapper.map(authorReqObj, Author.class);
        author.setDob(LocalDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);


    }

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable int id){
        return authorService.getAuthorById(id)
        .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @PutMapping("/author/{id}")
    public Author updateAuthor(@RequestBody Author author,@PathVariable int id){
       return authorService.getAuthorById(id)
       .map(author -> {
         author.setName(author.getName());
         author.setEmail(author.getEmail());
         author.setDOB(LocalDate);
         return authorService.save(author);
       })
       .orElseGet(() -> {
         author.setId(id);
         return authorService.save(author);
       });
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthor(id);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult=ex.getBindingResult();
        Map<String,String> errors=new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName =((FieldError) error).getField();
            String errorMessage= error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("Message" , "validation failed");
        errors.put("status",HttpStatus.BAD_REQUEST.name());
        errors.put("code", String.valueOf(HttpStatus.BAD_REQUEST.value()));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }
}


