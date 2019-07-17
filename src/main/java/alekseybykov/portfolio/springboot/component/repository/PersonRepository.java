package alekseybykov.portfolio.springboot.component.repository;

import alekseybykov.portfolio.springboot.component.entities.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> { }