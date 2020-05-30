package alekseybykov.portfolio.springboot.component.repository;

import alekseybykov.portfolio.springboot.component.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Unit test for repository: testing the Data layer in isolation from other layers.
 *
 * Actually, there is no need to testing functionality, provided by Spring Data.
 *
 * replace = NONE is needed for using the same database.
 *
 * @author Aleksey Bykov
 * @since 13.07.2019
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PersonRepositoryUnitTest {

    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    private TestEntityManager em;

    // initialized through the initFields(...) method
    private JacksonTester<Person> jacksonTester;

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
        Person person = new Person(NumberUtils.LONG_ONE, "A", "B");
        // when
        Person createdPerson = personRepository.save(person);
        // then
        assertThat(createdPerson.getId()).isEqualTo(NumberUtils.LONG_ONE);
        assertThat(createdPerson.getFirstName()).isEqualTo("A");
        assertThat(createdPerson.getLastName()).isEqualTo("B");
        assertThat(jacksonTester.write(createdPerson).getJson())
                .isEqualTo(jacksonTester.write(createdPerson).getJson());

        // --- read person ---

        //when
        Person foundedPerson = personRepository.findById(NumberUtils.LONG_ONE).get();
        // then
        assertThat(foundedPerson.getId()).isEqualTo(NumberUtils.LONG_ONE);
        assertThat(foundedPerson.getFirstName()).isEqualTo("A");
        assertThat(foundedPerson.getLastName()).isEqualTo("B");

        assertThat(jacksonTester.write(createdPerson).getJson())
                .isEqualTo(jacksonTester.write(foundedPerson).getJson());

        // --- update person ---

        // given
        Person personForUpdate = Person.builder().id(NumberUtils.LONG_ONE).firstName("C").lastName("D").build();
        // when
        Person updatedPerson = personRepository.save(personForUpdate);
        // then
        assertThat(updatedPerson.getId()).isEqualTo(NumberUtils.LONG_ONE);
        assertThat(updatedPerson.getFirstName()).isEqualTo("C");
        assertThat(updatedPerson.getLastName()).isEqualTo("D");
        assertThat(jacksonTester.write(personForUpdate).getJson())
                .isEqualTo(jacksonTester.write(updatedPerson).getJson());

        // --- delete person ---

        // given
        Person personForDelete = Person.builder().id(NumberUtils.LONG_ONE).build();
        // when
        personRepository.delete(personForDelete);
        // then
        Optional<Person> deletedPerson = personRepository.findById(NumberUtils.LONG_ONE);
        assertFalse(deletedPerson.isPresent());

        /*
          drop table person
         */
    }
}
