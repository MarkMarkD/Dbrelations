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
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class AnimalController {
    
    @Autowired
    AnimalRepository repository;
    
    @Autowired
    PersonRepository personRepository;
    
    @RequestMapping(value="/animal", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Animal> getAnimals() {
        return repository.findAll();
    }
    
    @GetMapping(value="/animal/{id}")
    public Optional<Animal> getAnimalById(@PathVariable Long id) {
        return repository.findById(id);
    }
    
    
    //FOR POSTMAN
    //localhost:8080/animal/add
    //{"name":"lion","desc":"lion","personid":2}
    @PostMapping(value="/animal/add")
    public Animal addAnimal(@RequestBody Animal animal) {
        Optional <Person> person = personRepository.findById(animal.getPersonid());
        Person newPerson = person.get();
        animal.setPerson(newPerson);
        repository.save(animal);
        return animal;
    }
    
    @GetMapping(value="/animal/{id}/delete")
    public ResponseEntity deleteAnimal(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    
}
