package alekseybykov.portfolio.springboot.component.mapping;

import java.util.List;

/**
 * @author Aleksey Bykov
 * @since 26.07.2019
 */
public interface BaseMapper<E, D> {

    List<D> toDtoList(List<E> entityList);

    D toDto(E entity);

    E toEntity(D dto);
}
