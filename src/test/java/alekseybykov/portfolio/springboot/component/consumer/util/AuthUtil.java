package alekseybykov.portfolio.springboot.component.consumer.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static java.util.Collections.singletonList;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-07-08
 */
public class  AuthUtil {
    public static <T> HttpEntity<T> createEntityWithBasicAuth(T body, MediaType mediaType,
            String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(singletonList(mediaType));
        httpHeaders.setBasicAuth(username, password);

        return new HttpEntity<>(body, httpHeaders);
    }
}
