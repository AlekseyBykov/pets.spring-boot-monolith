package alekseybykov.portfolio.springboot.component.mapping;

import java.util.List;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-26
 */
public interface BaseMapper<E, D> {

    List<D> toDtoList(List<E> entityList);

    D toDto(E entity);

    E toEntity(D dto);
}