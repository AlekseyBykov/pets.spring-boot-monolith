package alekseybykov.portfolio.springboot.component.domain;

import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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