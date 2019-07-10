package com.alekseybykov.examples.springboot.component.rest.api.dto;

import com.alekseybykov.examples.springboot.component.entities.Person;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public PersonDTO(Person person) {
        setId(person.getId());
        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
    }
}