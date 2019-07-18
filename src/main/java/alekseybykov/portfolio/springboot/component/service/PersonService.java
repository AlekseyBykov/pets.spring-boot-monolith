package alekseybykov.portfolio.springboot.component.service;

import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
public interface PersonService {

    Page<PersonDTO> getAllPersons(Pageable pageable);

    PersonDTO getPersonById(Long personId);

    PersonDTO addPerson(PersonDTO personDTO);

    PersonDTO updatePerson(PersonDTO personDTO);

    void deletePerson(Long personId);
}