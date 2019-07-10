package com.alekseybykov.examples.springboot.component.rest.api;

import com.alekseybykov.examples.springboot.component.rest.api.response.PositiveResponse;

import java.util.Collections;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-10
 */
public class ComponentAPI {
    private ComponentAPI() {
        throw new RuntimeException();
    }

    public static PositiveResponse positiveResponse(Object data) {
        return new PositiveResponse(data);
    }

    public static PositiveResponse emptyPositiveResponse() {
        return new PositiveResponse(Collections.emptyList());
    }
}