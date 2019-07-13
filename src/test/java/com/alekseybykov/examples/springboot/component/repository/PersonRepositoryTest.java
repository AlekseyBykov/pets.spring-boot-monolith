package com.alekseybykov.examples.springboot.component.repository;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.rest.api.dto.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private JacksonTester<PersonDTO> jacksonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @SneakyThrows
    public void testCreatePerson() {
        // create table person
        Person person = personRepository.save(new Person(1L, "A", "B"));
        assertThat(person.getId()).isEqualTo(1L);
        assertThat(person.getFirstName()).isEqualTo("A");
        assertThat(person.getLastName()).isEqualTo("B");

        assertThat(jacksonTester.write(new PersonDTO(person)).getJson())
                .isEqualTo(jacksonTester.write(PersonDTO.builder().id(1L).firstName("A").lastName("B").build()).getJson());
        // drop table person
    }
}