package com.alekseybykov.examples.springboot.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Person {
    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;
}