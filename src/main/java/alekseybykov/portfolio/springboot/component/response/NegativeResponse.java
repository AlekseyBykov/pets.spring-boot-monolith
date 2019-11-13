package alekseybykov.portfolio.springboot.component.response;

public class NegativeResponse extends Response {
    public NegativeResponse(StatusCode statusCode, String message, Object result) {
        super(statusCode, message, result);
    }
}
