package alekseybykov.portfolio.springboot.component.controller;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import alekseybykov.portfolio.springboot.component.mapping.PersonMapper;
import alekseybykov.portfolio.springboot.component.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static java.lang.String.format;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for controller: testing the Presentation layer
 * (in Application tier, REST) in isolation from other layers.
 *
 * Used @MockBean for bypassing the actual
 * dependency from the Business layer.
 *
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-14
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonMapper personMapper;

    // initialized through the initFields(...) method
    private JacksonTester<PersonDTO> jacksonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        // --- behaviour for mocked dependency ---
        PersonDTO personDTO = PersonDTO.builder().id(NumberUtils.LONG_ONE).firstName("A").lastName("B").build();
        Person person = Person.builder().id(NumberUtils.LONG_ONE).firstName("A").lastName("B").build();

        PersonDTO updatedPersonDTO = PersonDTO.builder().id(NumberUtils.LONG_ONE).firstName("C").lastName("D").build();
        Person updatedPerson = Person.builder().id(NumberUtils.LONG_ONE).firstName("C").lastName("D").build();

        Mockito.when(personMapper.toEntity(personDTO))
                .thenReturn(person);

        Mockito.when(personMapper.toEntity(updatedPersonDTO))
                .thenReturn(updatedPerson);

        Mockito.when(personMapper.toDto(person))
                .thenReturn(personDTO);

        Mockito.when(personService.addPerson(person))
                .thenReturn(person);

        Mockito.when(personService.getPersonById(person.getId()))
                .thenReturn(person);

        Mockito.when(personService.updatePerson(NumberUtils.LONG_ONE, updatedPerson))
                .thenReturn(updatedPerson);

        doAnswer((Answer<Void>) invocation -> {
            Object[] args = invocation.getArguments();
            System.out.println("Person entity was deleted. Called with the following arguments: " + Arrays.toString(args));
            return null;
        }).when(personService).deletePerson(person.getId());
    }

    @WithMockUser("USER")
    @Test
    @SneakyThrows
    public void testCRUD() {

        // --- create person ---
        PersonDTO personDTO = PersonDTO.builder().id(NumberUtils.LONG_ONE).firstName("A").lastName("B").build();
        mvc.perform(post("/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(personDTO).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id", is(NumberUtils.INTEGER_ONE)))
                .andExpect(jsonPath("$.result.firstName", is("A")))
                .andExpect(jsonPath("$.result.lastName", is("B")));

        // --- read person ---
        mvc.perform(get("/person/get/" + NumberUtils.LONG_ONE)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id", is(NumberUtils.INTEGER_ONE)))
                .andExpect(jsonPath("$.result.firstName", is("A")))
                .andExpect(jsonPath("$.result.lastName", is("B")));

        // --- update person ---
        PersonDTO updatedPersonDTO = PersonDTO.builder().id(NumberUtils.LONG_ONE).firstName("C").lastName("D").build();
        mvc.perform(put(format("%s=%d", "/person/update?id", NumberUtils.LONG_ONE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(updatedPersonDTO).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id", is(NumberUtils.INTEGER_ONE)))
                .andExpect(jsonPath("$.result.firstName", is("C")))
                .andExpect(jsonPath("$.result.lastName", is("D")));

        // --- delete person ---
        mvc.perform(delete(format("%s%d", "/person/delete/", NumberUtils.LONG_ONE))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", hasSize(NumberUtils.INTEGER_ZERO)));
    }
}
