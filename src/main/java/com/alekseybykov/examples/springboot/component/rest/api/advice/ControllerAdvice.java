package com.alekseybykov.examples.springboot.component.rest.api.advice;

import com.alekseybykov.examples.springboot.component.rest.api.ComponentAPI;
import com.alekseybykov.examples.springboot.component.rest.api.response.NegativeResponse;
import com.alekseybykov.examples.springboot.component.rest.api.status.StatusCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler.
 *
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-12
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public NegativeResponse handleException(MethodArgumentNotValidException exception) {
        return handleMethodArgumentNotValidException(exception);
    }

    private NegativeResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Validation error", exception);
        return ComponentAPI.negativeResponse(StatusCode.VALIDATION_ERROR, exception.getMessage(), exception);
    }
}