package com.alekseybykov.examples.springboot.component.entities;

import com.alekseybykov.examples.springboot.component.rest.api.dto.PersonDTO;
import com.google.common.base.Preconditions;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public Person(@NotNull PersonDTO personDTO) {
        Preconditions.checkNotNull(personDTO);

        setId(personDTO.getId());
        setFirstName(personDTO.getFirstName());
        setLastName(personDTO.getLastName());
    }
}