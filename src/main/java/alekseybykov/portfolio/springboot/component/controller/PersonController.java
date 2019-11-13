package alekseybykov.portfolio.springboot.component.controller;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import alekseybykov.portfolio.springboot.component.mapping.PersonMapper;
import alekseybykov.portfolio.springboot.component.response.Response;
import alekseybykov.portfolio.springboot.component.response.ResponseAPI;
import alekseybykov.portfolio.springboot.component.service.PersonService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * The controller communicates with the clients by using DTO (with JSON (de)serialization).
 * DTO is not used in other layers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    private static final String PERSONS_ID_ERROR_MSG = "Persons id must be specified";
    private static final String PERSONS_DETAILS_ERROR_MSG = "Persons details must be specified";

    @GetMapping("list")
    public Page<PersonDTO> getAllPersonsByPage(@RequestParam(value = "page") final Integer page,
                                               @RequestParam(value = "size") final Integer size) {
        Page<Person> data = personService.fetchPersonsByPages(page, size);
        return new PageImpl<>(personMapper.toDtoList(data.getContent()), data.getPageable(), data.getTotalElements());
    }

    @PostMapping("add")
    public Response addPerson(@RequestBody @Valid PersonDTO personDTO) {
        Preconditions.checkState(Objects.nonNull(personDTO), PERSONS_DETAILS_ERROR_MSG);
        Person person = personMapper.toEntity(personDTO);
        return ResponseAPI.positiveResponse(personService.addPerson(person));
    }

    @GetMapping("get/{id}")
    public Response getPersonByIdByUsingPathParam(@PathVariable("id") Long id) {
        Preconditions.checkState(Objects.nonNull(id), PERSONS_ID_ERROR_MSG);
        Person person = personService.getPersonById(id);
        return ResponseAPI.positiveResponse(personMapper.toDto(person));
    }

    @PutMapping("update")
    public Response updatePerson(@RequestParam(value = "id") Long id,
                                 @RequestBody @Valid PersonDTO personDTO) {
        Preconditions.checkState(Objects.nonNull(id), PERSONS_ID_ERROR_MSG);
        Preconditions.checkState(Objects.nonNull(personDTO), PERSONS_DETAILS_ERROR_MSG);
        Person person = personMapper.toEntity(personDTO);
        return ResponseAPI.positiveResponse(personService.updatePerson(id, person));
    }

    @DeleteMapping("delete/{id}")
    public Response deletePerson(@PathVariable("id") Long id) {
        Preconditions.checkState(Objects.nonNull(id), PERSONS_ID_ERROR_MSG);
        personService.deletePerson(id);
        return ResponseAPI.emptyPositiveResponse();
    }
}
