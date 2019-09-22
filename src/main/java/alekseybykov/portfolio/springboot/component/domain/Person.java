//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.springboot.component.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@Data
@Builder
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
}
