package com.alekseybykov.examples.springboot.component.consumer.util;

import org.springframework.http.*;

import static java.util.Collections.singletonList;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-08
 */
public class  AuthUtil {
    public static <T> HttpEntity<T> createEntityWithBasicAuth(T body, MediaType mediaType,
            String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(singletonList(mediaType));
        headers.setBasicAuth(username, password);

        return new HttpEntity<>(body, headers);
    }
}