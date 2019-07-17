package alekseybykov.portfolio.springboot.component.service;

import alekseybykov.portfolio.springboot.component.entities.Person;
import alekseybykov.portfolio.springboot.component.repository.PersonRepository;
import alekseybykov.portfolio.springboot.component.rest.api.dto.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

/**
 * Unit test for service: testing the Business layer in isolation from other layers.
 *
 * Used @MockBean for bypassing the actual
 * dependency from the Data layer.
 *
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-14
 */
@RunWith(SpringRunner.class)
public class PersonServiceUnitTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public PersonService personService() {
            return new PersonServiceImpl();
        }
    }

    @Autowired
    private PersonService personService;

    // initialized through the initFields(...) method
    private JacksonTester<PersonDTO> jacksonTester;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        // --- behaviour for mocked dependency ---
        Optional<Person> foundedPerson = Optional.of(new Person(1L, "A", "B"));
        Mockito.when(personRepository.findById(1L))
                .thenReturn(foundedPerson);

        Person createdPerson = new Person(1L, "A", "B");
        Mockito.when(personRepository.save(createdPerson))
                .thenReturn(createdPerson);

        Person updatedPerson = new Person(1L, "C", "D");
        Mockito.when(personRepository.save(updatedPerson))
                .thenReturn(updatedPerson);

        Person deletedPerson = new Person(1L, null, null);

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
        PersonDTO personDTO = PersonDTO.builder().id(1L).firstName("A").lastName("B").build();
        // when
        PersonDTO createdPersonDTO = personService.addPerson(personDTO);
        // then
        assertThat(jacksonTester.write(personDTO).getJson())
                .isEqualTo(jacksonTester.write(createdPersonDTO).getJson());

        // --- read person ---
        // when
        PersonDTO foundedPersonDTO = personService.getPersonById(1L);
        // then
        assertThat(jacksonTester.write(foundedPersonDTO).getJson())
                .isEqualTo(jacksonTester.write(createdPersonDTO).getJson());

        // --- update person ---
        // given
        PersonDTO personDtoForUpdate = PersonDTO.builder().id(1L).firstName("C").lastName("D").build();
        // when
        PersonDTO updatePersonDTO = personService.updatePerson(personDtoForUpdate);
        // then
        assertThat(jacksonTester.write(updatePersonDTO).getJson())
                .isEqualTo(jacksonTester.write(personDtoForUpdate).getJson());

        // --- delete ---
        Person personDtoForDelete = new Person(1L, null, null);
        // when
        personService.deletePerson(personDtoForDelete.getId());
        // then
        // fixme add assertion
    }
}