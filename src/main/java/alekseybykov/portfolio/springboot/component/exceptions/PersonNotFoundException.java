package alekseybykov.portfolio.springboot.component.exceptions;

/**
 * @author Aleksey Bykov
 * @since 11.08.2019
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {}

    public PersonNotFoundException(String msg) {
        super(msg);
    }
}
