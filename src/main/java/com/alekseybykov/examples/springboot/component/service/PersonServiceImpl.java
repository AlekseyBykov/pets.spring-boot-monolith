package com.alekseybykov.examples.springboot.component.service;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(list::add);

        return list;
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public boolean addPerson(Person person) {
        List<Person> list = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        if(list.size() > 0) {
            return false;
        } else {
            personRepository.save(person);
            return true;
        }
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        personRepository.delete(getPersonById(personId));
    }
}