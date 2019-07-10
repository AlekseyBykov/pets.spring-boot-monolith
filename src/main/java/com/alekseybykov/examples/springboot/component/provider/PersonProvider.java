package com.alekseybykov.examples.springboot.component.provider;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.alekseybykov.examples.springboot.component.rest.api.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonProvider {
    public Person buildPerson(PersonDTO personDTO) {
        return new Person(personDTO);
    }
}