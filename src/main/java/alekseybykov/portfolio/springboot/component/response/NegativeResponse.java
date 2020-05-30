package alekseybykov.portfolio.springboot.component.response;

/**
 * @author Aleksey Bykov
 * @since 12.07.2019
 */
public class NegativeResponse extends Response {
    public NegativeResponse(StatusCode statusCode, String message, Object result) {
        super(statusCode, message, result);
    }
}
