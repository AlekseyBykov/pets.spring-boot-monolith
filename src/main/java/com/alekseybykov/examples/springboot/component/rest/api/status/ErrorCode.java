package com.alekseybykov.examples.springboot.component.rest.api.status;

import lombok.Getter;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
@Getter
public enum ErrorCode {
    EMPTY_ERROR(0);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}