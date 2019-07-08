package com.alekseybykov.examples.springboot.component.consumer.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-08
 */
@Data
@Builder
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
}