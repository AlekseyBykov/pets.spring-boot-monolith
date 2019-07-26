package alekseybykov.portfolio.springboot.component.service;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import org.springframework.data.domain.Page;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
public interface PersonService {

    Page<Person> fetchPersonsByPages(Integer page, Integer size);

    PersonDTO getPersonById(Long personId);

    PersonDTO addPerson(PersonDTO personDTO);

    PersonDTO updatePerson(PersonDTO personDTO);

    void deletePerson(Long personId);
}