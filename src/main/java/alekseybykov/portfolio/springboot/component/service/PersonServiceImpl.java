package alekseybykov.portfolio.springboot.component.service;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.exceptions.PersonNotFoundException;
import alekseybykov.portfolio.springboot.component.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aleksey Bykov
 * @since 27.06.2019
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Person> fetchPersonsByPages(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return personRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Person updatePerson(Long id, Person person) {
        Person foundedPerson = getPersonById(id);
        foundedPerson.setFirstName(person.getFirstName());
        foundedPerson.setLastName(person.getLastName());
        return personRepository.save(foundedPerson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePerson(Long id) {
        Person foundedPerson = getPersonById(id);
        personRepository.delete(foundedPerson);
    }
}
