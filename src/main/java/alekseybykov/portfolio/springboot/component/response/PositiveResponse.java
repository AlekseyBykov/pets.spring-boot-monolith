//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.springboot.component.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Getter
@ToString
public class PositiveResponse extends Response {
    public PositiveResponse(Object result) {
        super(result);
    }
}
