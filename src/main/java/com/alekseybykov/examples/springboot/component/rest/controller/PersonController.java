package com.alekseybykov.examples.springboot.component.rest.controller;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.rest.api.ComponentAPI;
import com.alekseybykov.examples.springboot.component.rest.api.dto.PersonDTO;
import com.alekseybykov.examples.springboot.component.rest.api.response.Response;
import com.alekseybykov.examples.springboot.component.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Response addPerson(@RequestBody @Valid PersonDTO personDTO) {
        return ComponentAPI.positiveResponse(personService.addPerson(personDTO));
    }

    @GetMapping("list")
    public Response getAllPersons() {
        return ComponentAPI.positiveResponse(personService.getAllPersons());
    }

    @GetMapping("get/{id}")
    public Response getPersonByIdByUsingPathParam(@PathVariable("id") Long id) {
        return ComponentAPI.positiveResponse(personService.getPersonById(id));
    }

    @GetMapping("get")
    public Response getPersonByIdByUsingRequestParam(@RequestParam("id") Long id) {
        return ComponentAPI.positiveResponse(personService.getPersonById(id));
    }

    @PutMapping("update")
    public Response updatePerson(@RequestBody @Valid PersonDTO personDTO) {
        return ComponentAPI.positiveResponse(personService.updatePerson(personDTO));
    }

    @DeleteMapping("delete/{id}")
    public Response deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return ComponentAPI.emptyPositiveResponse();
    }
}