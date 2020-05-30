package alekseybykov.portfolio.springboot.component.response;

import java.util.Collections;

/**
 * @author Aleksey Bykov
 * @since 10.07.2019
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
