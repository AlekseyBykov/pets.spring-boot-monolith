package com.alekseybykov.examples.springboot.component.service;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.provider.PersonProvider;
import com.alekseybykov.examples.springboot.component.repository.PersonRepository;
import com.alekseybykov.examples.springboot.component.rest.api.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonProvider personProvider;
    private final PersonRepository personRepository;

    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(list::add);
        return list;
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public Person addPerson(PersonDTO personDTO) {
        Person person = personProvider.buildPerson(personDTO);
        return personRepository.save(person);
    }

    public Person updatePerson(PersonDTO personDTO) {
        Person person = personProvider.buildPerson(personDTO);
        return personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        personRepository.delete(getPersonById(personId));
    }
}