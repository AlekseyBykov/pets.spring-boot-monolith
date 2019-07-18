package alekseybykov.portfolio.springboot.component.advice;

import alekseybykov.portfolio.springboot.component.response.ResponseAPI;
import alekseybykov.portfolio.springboot.component.response.NegativeResponse;
import alekseybykov.portfolio.springboot.component.response.StatusCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-12
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public NegativeResponse handleException(Throwable throwable) {
        // for handling chained exceptions
        Throwable cause = Optional.ofNullable(throwable.getCause()).orElse(throwable);
//        if (cause instanceof ValidationException) {
//            return handleValidationException((ValidationException) cause);
//        }
//        etc...

        return handleDefault(cause);
    }

    private NegativeResponse handleDefault(Throwable throwable) {
        log.error("Error while processing the request", throwable);
        return ResponseAPI.negativeResponse(StatusCode.INTERNAL_SERVER_ERROR,
                defaultIfNull(throwable.getMessage(), "Error while processing the request"), throwable);
    }
}