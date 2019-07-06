package com.alekseybykov.examples.springboot.component.entities;

import lombok.*;

import javax.persistence.*;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;
}