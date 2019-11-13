package alekseybykov.portfolio.springboot.component.response;

import lombok.Getter;

@Getter
public enum StatusCode {
    OK(0),
    INTERNAL_SERVER_ERROR(1);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }
}
