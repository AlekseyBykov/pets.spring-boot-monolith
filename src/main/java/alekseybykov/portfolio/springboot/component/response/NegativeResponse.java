//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.springboot.component.response;

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
