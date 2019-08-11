package alekseybykov.portfolio.springboot.component.repository;

import alekseybykov.portfolio.springboot.component.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
@Repository
public interface PersonRepository extends /*PersonRegistry, */JpaRepository<Person, Long> {
    Page<Person> findAll(Pageable pageable);
}
