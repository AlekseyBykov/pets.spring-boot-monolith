package com.alekseybykov.examples.springboot.component.service;

import com.alekseybykov.examples.springboot.component.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(Long personId);

    boolean addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long personId);
}