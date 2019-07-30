package alekseybykov.portfolio.springboot.component.response;

import java.util.Collections;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
public class ResponseAPI {
    private ResponseAPI() { }

    public static PositiveResponse positiveResponse(Object data) {
        return new PositiveResponse(data);
    }

    public static PositiveResponse emptyPositiveResponse() {
        return new PositiveResponse(Collections.emptyList());
    }

    public static NegativeResponse negativeResponse(StatusCode statusCode, String message, Object result) {
        return new NegativeResponse(statusCode, message, result);
    }
}