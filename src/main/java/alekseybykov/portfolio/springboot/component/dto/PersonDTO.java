package alekseybykov.portfolio.springboot.component.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Aleksey Bykov
 * @since 10.07.2019
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
}
