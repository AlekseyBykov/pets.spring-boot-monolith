package alekseybykov.portfolio.springboot.component;

import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import alekseybykov.portfolio.springboot.component.repository.PersonRepository;
import alekseybykov.portfolio.springboot.component.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing layers integration, end-to-end.
 * No mocks is used.
 *
 * @author Aleksey Bykov
 * @since 15.07.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes = SpringBootExample.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-it.properties")
public class SpringBootExampleIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    private JacksonTester<PersonDTO> jacksonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @WithMockUser("USER")
    @Test
    @SneakyThrows
    public void testLayersIntegration() {
        PersonDTO dto = PersonDTO.builder().id(NumberUtils.LONG_ONE).firstName("A").lastName("B").build();
        mvc.perform(post("/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jacksonTester.write(dto).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id", is(NumberUtils.INTEGER_ONE)))
                .andExpect(jsonPath("$.result.firstName", is("A")))
                .andExpect(jsonPath("$.result.lastName", is("B")));
    }
}
