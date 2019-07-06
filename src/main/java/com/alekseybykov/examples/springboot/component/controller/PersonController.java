package com.alekseybykov.examples.springboot.component.controller;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

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
    public ResponseEntity<Person> getPersonByIdByUsingPathParam(@PathVariable("id") Long id) {
        Person person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<Person> getPersonByIdByUsingRequestParam(@RequestParam("id") Long id) {
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