//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.springboot.component.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Getter
@RequiredArgsConstructor
public abstract class Response implements Serializable {

    private final StatusCode statusCode;
    private final String message;
    private final Object result;

    public Response(Object result) {
        this.statusCode = StatusCode.OK;
        this.message = StringUtils.EMPTY;
        this.result = result;
    }
}
