/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banashko.dbrelations.controller;

import com.banashko.dbrelations.domain.Book;
import com.banashko.dbrelations.domain.Person;
import com.banashko.dbrelations.repository.AuthorRepository;
import com.banashko.dbrelations.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author banashko.dv
 */
@RestController
public class BookController {
    
    @Autowired
    private BookRepository repository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @RequestMapping(value="/book", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getPersons() {
        return repository.findAll();
    }
    
    @GetMapping(value="/book/{id}")
    public Optional<Book> getAnimalById(@PathVariable Long id) {
        return repository.findById(id);
    }
}
