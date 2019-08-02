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
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("list")
    public Page<PersonDTO> getAllPersonsByPage(@RequestParam(value = "page") final Integer page,
                                               @RequestParam(value = "size") final Integer size) {
        Page<Person> data = personService.fetchPersonsByPages(page, size);
        return new PageImpl<>(personMapper.toDtoList(data.getContent()), data.getPageable(), data.getTotalElements());
    }

    @PostMapping("add")
    public Response addPerson(@RequestBody @Valid PersonDTO personDTO) {
        Preconditions.checkState(Objects.nonNull(personDTO), "Persons details must be specified");
        return ResponseAPI.positiveResponse(personService.addPerson(personDTO));
    }

    @GetMapping("get/{id}")
    public Response getPersonByIdByUsingPathParam(@PathVariable("id") Long id) {
        Preconditions.checkState(Objects.nonNull(id), "Persons id must be specified");
        return ResponseAPI.positiveResponse(personService.getPersonById(id));
    }

    @PutMapping("update")
    public Response updatePerson(@RequestBody @Valid PersonDTO personDTO) {
        Preconditions.checkState(Objects.nonNull(personDTO), "Persons details must be specified");
        return ResponseAPI.positiveResponse(personService.updatePerson(personDTO));
    }

    @DeleteMapping("delete/{id}")
    public Response deletePerson(@PathVariable("id") Long id) {
        Preconditions.checkState(Objects.nonNull(id), "Persons id must be specified");
        personService.deletePerson(id);
        return ResponseAPI.emptyPositiveResponse();
    }
}