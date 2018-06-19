/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banashko.dbrelations.controller;

import com.banashko.dbrelations.domain.Animal;
import com.banashko.dbrelations.domain.Person;
import com.banashko.dbrelations.repository.AnimalRepository;
import com.banashko.dbrelations.repository.PersonRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author banashko.dv
 */
@RestController
public class PersonController {
    
    @Autowired
    PersonRepository repository;
    
    @Autowired
    AnimalRepository animalRepository;
    
    @RequestMapping(value="/person", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersons() {
        return repository.findAll();
    }
    
    @GetMapping(value="/person/{id}")
    public Optional<Person> getAnimalById(@PathVariable Long id) {
        return repository.findById(id);
    }
    
    //FOR POSTMAN:
    //localhost:8080/person/add
    //{"name":"Barhama","age":94,"animals":[{"name":"Jerry","desc":"mouse"}]}
    @PostMapping(value="/person/add")
    public Person addPerson(@RequestBody Person person) {
        
        Set<Animal> animals = person.getAnimals();
        person.setAnimals(new HashSet<>());
        repository.save(person);
        
        //Initialization by name is very very bad and I shouldn't do it that way, but.. Who cares =)
        Person newPerson = repository.findPersonByName(person.getName());
        for (Animal a : animals) {
            a.setPerson(newPerson);
            animalRepository.save(a);
        }
        
        return newPerson;
    }
    
    @GetMapping(value="/person/{id}/delete")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
