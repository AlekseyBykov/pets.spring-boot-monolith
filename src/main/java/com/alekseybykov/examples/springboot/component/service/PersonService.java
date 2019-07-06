package com.alekseybykov.examples.springboot.component.service;

import com.alekseybykov.examples.springboot.component.entities.Person;

import java.util.List;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(Long personId);

    boolean addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long personId);
}