package com.alekseybykov.examples.springboot.component.rest.api.response;

import com.alekseybykov.examples.springboot.component.rest.api.status.StatusCode;

/**
 * Global exception handler.
 *
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-12
 */
public class NegativeResponse extends Response {
    public NegativeResponse(StatusCode code, String message, Object result) {
        super(code, message, result);
    }
}