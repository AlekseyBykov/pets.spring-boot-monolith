package alekseybykov.portfolio.springboot.component.mapping;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import org.mapstruct.Mapper;

/**
 * @author Aleksey Bykov
 * @since 26.07.2019
 */
@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<Person, PersonDTO> { }
