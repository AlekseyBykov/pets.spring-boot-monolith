package alekseybykov.portfolio.springboot.component.service;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.json.JacksonTester;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * Clean Unit test for service: testing the Business layer in isolation from other layers
 * and without Spring context.
 *
 * Used mock() for bypassing the actual
 * dependency from the Data layer.
 *
 * Injects mock bean through constructor (lombok annotation).
 *
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-14
 */
public class PersonServiceUnitTest {

    // initialized through the initFields(...) method
    private JacksonTester<Person> jacksonTester;

    private PersonRepository personRepository = mock(PersonRepository.class);

    private PersonService personService = new PersonServiceImpl(personRepository);

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        // --- behaviour for mocked dependency ---
        Optional<Person> foundedPerson = Optional.of(new Person(NumberUtils.LONG_ONE, "A", "B"));
        Mockito.when(personRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(foundedPerson);

        Person createdPerson = new Person(NumberUtils.LONG_ONE, "A", "B");
        Mockito.when(personRepository.save(createdPerson))
                .thenReturn(createdPerson);

        Person updatedPerson = new Person(NumberUtils.LONG_ONE, "C", "D");
        Mockito.when(personRepository.save(updatedPerson))
                .thenReturn(updatedPerson);

        Person deletedPerson = new Person(NumberUtils.LONG_ONE, null, null);

        doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            System.out.println("Person entity was deleted. Called with the following arguments: " + Arrays.toString(args));
            return null;
        }).when(personRepository).delete(deletedPerson);
    }

    @Test
    @SneakyThrows
    public void testCRUD() {
        // --- create person ---
        // given
        Person person = Person.builder().id(NumberUtils.LONG_ONE).firstName("A").lastName("B").build();
        // when
        Person createdPerson = personService.addPerson(person);
        // then
        assertThat(jacksonTester.write(person).getJson())
                .isEqualTo(jacksonTester.write(createdPerson).getJson());

        // --- read person ---
        // when
        Person foundedPerson = personService.getPersonById(NumberUtils.LONG_ONE);
        // then
        assertThat(jacksonTester.write(foundedPerson).getJson())
                .isEqualTo(jacksonTester.write(createdPerson).getJson());

        // --- update person ---
        // given
        Person personForUpdate = Person.builder().id(NumberUtils.LONG_ONE).firstName("C").lastName("D").build();
        // when
        Person updatedPerson = personService.updatePerson(NumberUtils.LONG_ONE, personForUpdate);
        // then
        assertThat(jacksonTester.write(updatedPerson).getJson())
                .isEqualTo(jacksonTester.write(personForUpdate).getJson());

        // --- delete ---
        Person personDtoForDelete = new Person(NumberUtils.LONG_ONE, null, null);
        // when
        personService.deletePerson(personDtoForDelete.getId());
        // then
        // fixme add assertion
    }
}