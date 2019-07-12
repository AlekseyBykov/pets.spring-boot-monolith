package com.alekseybykov.examples.springboot.component.rest.api.response;

import com.alekseybykov.examples.springboot.component.rest.api.status.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static com.alekseybykov.examples.springboot.component.rest.api.status.StatusCode.OK;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Getter
@RequiredArgsConstructor
public abstract class Response implements Serializable {

    private final StatusCode statusCode;
    private final String message;
    private final Object result;

    public Response(Object result) {
        this.statusCode = OK;
        this.message = StringUtils.EMPTY;
        this.result = result;
    }
}