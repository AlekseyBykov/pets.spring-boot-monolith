package alekseybykov.portfolio.springboot.component.mapping;

import alekseybykov.portfolio.springboot.component.domain.Person;
import alekseybykov.portfolio.springboot.component.dto.PersonDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * @author Aleksey Bykov
 * @since 01.08.2019
 */
public class PersonMapperTest {

    private PersonMapper mapper;

    @Before
    public void setup() {
        mapper = Mappers.getMapper(PersonMapper.class);
    }

    @Test
    public void testToDtoList() {
        List<Person> personList = Stream.of(
            new Person(NumberUtils.LONG_ONE, "A", "B"),
            new Person(NumberUtils.LONG_MINUS_ONE, "C", "D")
        ).collect(Collectors.toList());

        List<PersonDTO> personDTOList = mapper.toDtoList(personList);

        assertEquals(personDTOList.get(0).getId(), personList.get(0).getId());
        assertEquals(personDTOList.get(0).getFirstName(), personList.get(0).getFirstName());
        assertEquals(personDTOList.get(0).getLastName(), personList.get(0).getLastName());

        assertEquals(personDTOList.get(1).getId(), personList.get(1).getId());
        assertEquals(personDTOList.get(1).getFirstName(), personList.get(1).getFirstName());
        assertEquals(personDTOList.get(1).getLastName(), personList.get(1).getLastName());
    }
}
