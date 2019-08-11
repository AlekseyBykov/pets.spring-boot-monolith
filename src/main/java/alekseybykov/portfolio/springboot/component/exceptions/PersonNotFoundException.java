package alekseybykov.portfolio.springboot.component.exceptions;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-08-10
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {}

    public PersonNotFoundException(String msg) {
        super(msg);
    }
}
