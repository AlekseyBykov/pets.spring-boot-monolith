package alekseybykov.portfolio.springboot.component.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {}

    public PersonNotFoundException(String msg) {
        super(msg);
    }
}
