package alekseybykov.portfolio.springboot.component.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Aleksey Bykov
 * @since 10.07.2019
 */
@Getter
@ToString
public class PositiveResponse extends Response {
    public PositiveResponse(Object result) {
        super(result);
    }
}
