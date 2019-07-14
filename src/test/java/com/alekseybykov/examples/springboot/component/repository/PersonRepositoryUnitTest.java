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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Unit test for repository: testing the Data layer in isolation from other layers.
 *
 * replace = NONE is needed for using the same
 * MS SQL database.
 *
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PersonRepositoryUnitTest {

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    private TestEntityManager em;

    private JacksonTester<PersonDTO> jacksonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @SneakyThrows
    public void testCRUD() {
        /*
          create table person
         */

        // --- create person ---

        // given
        Person person = new Person(1L, "A", "B");
        PersonDTO createdPersonDTO = PersonDTO.builder().id(1L).firstName("A").lastName("B").build();

        // when
        Person createdPerson = personRepository.save(person);
        // then
        assertThat(createdPerson.getId()).isEqualTo(1L);
        assertThat(createdPerson.getFirstName()).isEqualTo("A");
        assertThat(createdPerson.getLastName()).isEqualTo("B");
        assertThat(jacksonTester.write(new PersonDTO(createdPerson)).getJson())
                .isEqualTo(jacksonTester.write(createdPersonDTO).getJson());

        // --- read person ---

        //when
        Person foundedPerson = personRepository.findById(1L).get();
        // then
        assertThat(foundedPerson.getId()).isEqualTo(1L);
        assertThat(foundedPerson.getFirstName()).isEqualTo("A");
        assertThat(foundedPerson.getLastName()).isEqualTo("B");

        PersonDTO foundedPersonDTO = new PersonDTO(foundedPerson);
        assertThat(jacksonTester.write(createdPersonDTO).getJson())
                .isEqualTo(jacksonTester.write(foundedPersonDTO).getJson());

        // --- update person ---

        // given
        PersonDTO updatedPersonDTO = PersonDTO.builder().id(1L).firstName("C").lastName("D").build();
        // when
        Person updatedPerson = personRepository.save(new Person(updatedPersonDTO));
        // then
        assertThat(updatedPerson.getId()).isEqualTo(1L);
        assertThat(updatedPerson.getFirstName()).isEqualTo("C");
        assertThat(updatedPerson.getLastName()).isEqualTo("D");
        assertThat(jacksonTester.write(updatedPersonDTO).getJson())
                .isEqualTo(jacksonTester.write(new PersonDTO(updatedPerson)).getJson());

        // --- delete person ---

        // given
        PersonDTO deletedPersonDTO = PersonDTO.builder().id(1L).build();
        // when
        personRepository.delete(new Person(deletedPersonDTO));
        // then
        Optional<Person> deletedPerson = personRepository.findById(1L);
        assertFalse(deletedPerson.isPresent());

        /*
          drop table person
         */
    }
}