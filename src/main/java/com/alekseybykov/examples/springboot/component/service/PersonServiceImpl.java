package com.alekseybykov.examples.springboot.component.service;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.repository.PersonRepository;
import com.alekseybykov.examples.springboot.component.rest.api.dto.PersonDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@NoArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Page<PersonDTO> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable).map(PersonDTO::new);
    }

    public PersonDTO getPersonById(Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        return person.map(PersonDTO::new).orElse(null);
    }

    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = new Person(personDTO);
        return new PersonDTO(personRepository.save(person));
    }

    public PersonDTO updatePerson(PersonDTO personDTO) {
        Person person = new Person(personDTO);
        return new PersonDTO(personRepository.save(person));
    }

    public void deletePerson(Long personId) {
        Person person = new Person(getPersonById(personId));
        personRepository.delete(person);
    }
}