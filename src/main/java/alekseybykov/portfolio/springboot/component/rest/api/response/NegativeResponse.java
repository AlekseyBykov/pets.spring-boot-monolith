package alekseybykov.portfolio.springboot.component.rest.api.response;

import alekseybykov.portfolio.springboot.component.rest.api.status.StatusCode;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-12
 */
public class NegativeResponse extends Response {
    public NegativeResponse(StatusCode statusCode, String message, Object result) {
        super(statusCode, message, result);
    }
}