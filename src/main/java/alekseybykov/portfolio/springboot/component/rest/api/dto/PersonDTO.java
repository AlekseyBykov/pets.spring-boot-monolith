package alekseybykov.portfolio.springboot.component.rest.api.dto;

import alekseybykov.portfolio.springboot.component.entities.Person;
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

    @NotNull(message = "Person's id cannot be null")
    private Long id;

    @NotNull(message = "Person's first name cannot be empty")
    private String firstName;

    @NotNull(message = "Person's last name cannot be empty")
    private String lastName;

    public PersonDTO(Person person) {
        setId(person.getId());
        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
    }
}