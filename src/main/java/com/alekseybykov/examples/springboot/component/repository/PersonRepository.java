package com.alekseybykov.examples.springboot.component.repository;

import com.alekseybykov.examples.springboot.component.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String LastName);
    List<Person> findByFirstNameAndLastName(String firstName, String LastName);
}