package com.alekseybykov.examples.springboot.component.repository;

import com.alekseybykov.examples.springboot.component.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String LastName);
    List<Person> findByFirstNameAndLastName(String firstName, String LastName);
}