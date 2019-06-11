package com.alekseybykov.examples.springboot.controller;

import com.alekseybykov.examples.springboot.entities.Person;
import com.alekseybykov.examples.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("add")
    public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        boolean flag = personService.addPerson(person);

        if(!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/add/{id}").buildAndExpand(person.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = personService.getAllPersons();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {
        Person person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}