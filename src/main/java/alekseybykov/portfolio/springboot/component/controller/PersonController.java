package alekseybykov.portfolio.springboot.component.controller;

import alekseybykov.portfolio.springboot.component.response.ResponseAPI;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import alekseybykov.portfolio.springboot.component.response.Response;
import alekseybykov.portfolio.springboot.component.service.PersonService;

import com.google.common.base.Preconditions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("list")
    public Response getAllPersonsByPage(@RequestParam(value = "page") final Integer page,
                                        @RequestParam(value = "size") final Integer size) {
        return ResponseAPI.positiveResponse(personService.fetchPersonsByPages(page, size));
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