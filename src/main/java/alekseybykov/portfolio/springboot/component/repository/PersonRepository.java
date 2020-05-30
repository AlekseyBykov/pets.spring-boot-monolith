package alekseybykov.portfolio.springboot.component.repository;

import alekseybykov.portfolio.springboot.component.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aleksey Bykov
 * @since 11.06.2019
 */
@Repository
public interface PersonRepository extends /*PersonRegistry, */JpaRepository<Person, Long> {
    Page<Person> findAll(Pageable pageable);
}
