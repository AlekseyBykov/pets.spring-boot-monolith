package alekseybykov.portfolio.springboot.component.mapping;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import org.mapstruct.Mapper;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-26
 */
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonDTO> { }