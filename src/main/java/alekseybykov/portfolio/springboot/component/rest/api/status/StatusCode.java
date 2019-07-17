package alekseybykov.portfolio.springboot.component.rest.api.status;

import lombok.Getter;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Getter
public enum StatusCode {
    OK(0),
    INTERNAL_SERVER_ERROR(1);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }
}