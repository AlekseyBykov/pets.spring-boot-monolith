package com.alekseybykov.examples.springboot.component.rest.api.response;

import com.alekseybykov.examples.springboot.component.rest.api.status.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

import static com.alekseybykov.examples.springboot.component.rest.api.status.ErrorCode.NO_ERROR;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Getter
@RequiredArgsConstructor
public abstract class Response implements Serializable {

    private final ErrorCode errorCode;
    private final String errorMessage;
    private final Object result;

    Response(Object result) {
        this.errorCode = NO_ERROR;
        this.errorMessage = StringUtils.EMPTY;
        this.result = result;
    }
}