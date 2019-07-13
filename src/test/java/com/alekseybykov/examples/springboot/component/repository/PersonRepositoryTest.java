package com.alekseybykov.examples.springboot.component.repository;

import com.alekseybykov.examples.springboot.component.entities.Person;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @SneakyThrows
    public void testCreatePerson() {
        Person person = personRepository.save(new Person(1L, "A", "B"));
        assertThat(person.getId()).isEqualTo(1L);
        assertThat(person.getFirstName()).isEqualTo("A");
        assertThat(person.getLastName()).isEqualTo("B");
    }
}