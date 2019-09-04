package alekseybykov.portfolio.springboot.component.consumer;

import alekseybykov.portfolio.springboot.component.consumer.dto.PersonDTO;
import alekseybykov.portfolio.springboot.component.consumer.util.AuthUtil;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 1.0
 * @since   2019-06-11
 */
public class RestClient {
    private final static String username = "user";
    private final static String password = "user";
    private final static String contextPath = "http://localhost:8080/application";

    public static void main(String args[]) {
        RestClient client = new RestClient();
        // CRUD
        client.createPerson();
        client.readPerson();
        client.updatePerson();
        client.deletePerson();

        // Testing pagination
        for(long i = 0; i < 100; i++) {
            client.createPersonWithAssignedId(i);
        }

        client.getFirstPageWithTenElements();
        client.getFirstPageWithFifteenElements();
    }

    private void createPersonWithAssignedId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s", contextPath, "person/add");

        PersonDTO dto = PersonDTO.builder()
                .id(id)
                .firstName(format("%s%03d", "A", id))
                .lastName(format("%s%03d", "B", id))
                .build();

        restTemplate.exchange(url, HttpMethod.POST, AuthUtil.createEntityWithBasicAuth(
            dto, MediaType.ALL, username, password), String.class);
    }

    private void getFirstPageWithTenElements() {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s", contextPath, "person/list?page=0&size=10");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print <200, {
        //  "statusCode": "OK",
        //  "message": "",
        //  "result": {
        //    "content": [
        //      {
        //        "id": 0,
        //        "firstName": "A000",
        //        "lastName": "B000"
        //      },
        //      {
        //        "id": 1,
        //        "firstName": "A001",
        //        "lastName": "B001"
        //      },
        //      {
        //        "id": 2,
        //        "firstName": "A002",
        //        "lastName": "B002"
        //      },
        //
        // SKIPPED ...
        //
        //      {
        //        "id": 8,
        //        "firstName": "A008",
        //        "lastName": "B008"
        //      },
        //      {
        //        "id": 9,
        //        "firstName": "A009",
        //        "lastName": "B009"
        //      }
        //    ],
        //    "pageable": {
        //      "sort": {
        //        "sorted": false,
        //        "unsorted": true,
        //        "empty": true
        //      },
        //      "offset": 0,
        //      "pageSize": 10,
        //      "pageNumber": 0,
        //      "unpaged": false,
        //      "paged": true
        //    },
        //    "totalElements": 100,
        //    "totalPages": 10,
        //    "last": false,
        //    "size": 10,
        //    "number": 0,
        //    "sort": {
        //      "sorted": false,
        //      "unsorted": true,
        //      "empty": true
        //    },
        //    "numberOfElements": 10,
        //    "first": true,
        //    "empty": false
        //  }
        //},
        // [ Additional headers skipped here ...]>
        System.out.println(responseEntity.toString());
    }

    private void getFirstPageWithFifteenElements() {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s", contextPath, "person/list?page=0&size=15");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print <200, {
        //  "statusCode": "OK",
        //  "message": "",
        //  "result": {
        //    "content": [
        //      {
        //        "id": 0,
        //        "firstName": "A000",
        //        "lastName": "B000"
        //      },
        //      {
        //        "id": 1,
        //        "firstName": "A001",
        //        "lastName": "B001"
        //      },
        //      {
        //        "id": 2,
        //        "firstName": "A002",
        //        "lastName": "B002"
        //      },
        //      {
        //        "id": 3,
        //        "firstName": "A003",
        //        "lastName": "B003"
        //      },
        //
        // SKIPPED ...
        //
        //      {
        //        "id": 13,
        //        "firstName": "A013",
        //        "lastName": "B013"
        //      },
        //      {
        //        "id": 14,
        //        "firstName": "A014",
        //        "lastName": "B014"
        //      }
        //    ],
        //    "pageable": {
        //      "sort": {
        //        "sorted": false,
        //        "unsorted": true,
        //        "empty": true
        //      },
        //      "offset": 0,
        //      "pageSize": 15,
        //      "pageNumber": 0,
        //      "unpaged": false,
        //      "paged": true
        //    },
        //    "totalElements": 100,
        //    "totalPages": 7,
        //    "last": false,
        //    "size": 15,
        //    "number": 0,
        //    "sort": {
        //      "sorted": false,
        //      "unsorted": true,
        //      "empty": true
        //    },
        //    "numberOfElements": 15,
        //    "first": true,
        //    "empty": false
        //  }
        //},
        // [ Additional headers skipped here ...]>
        System.out.println(responseEntity.toString());
    }

    private void createPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s", contextPath, "person/add");

        PersonDTO dto = PersonDTO.builder()
                .id(NumberUtils.LONG_ONE)
                .firstName("A")
                .lastName("B")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, AuthUtil.createEntityWithBasicAuth(
                        dto, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print {"statusCode":"OK","message":"","result":{"id":1,"firstName":"A","lastName":"B"}}
        System.out.println(responseEntity.toString());
    }

    private void readPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s", contextPath, "person/get/1");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print {"statusCode":"OK","message":"","result":{"id":1,"firstName":"A","lastName":"B"}}
        System.out.println(responseEntity.toString());
    }

    private void updatePerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s=%d", contextPath, "person/update?id", NumberUtils.LONG_ONE);

        PersonDTO dto = PersonDTO.builder()
                .id(NumberUtils.LONG_ONE)
                .firstName("C")
                .lastName("D")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, AuthUtil.createEntityWithBasicAuth(
                        dto, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print {"statusCode":"OK","message":"","result":{"id":1,"firstName":"C","lastName":"D"}}
        System.out.println(responseEntity.toString());
    }

    private void deletePerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = format("%s/%s", contextPath, "person/delete/1");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.DELETE, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print {"statusCode":"OK","message":"","result":[]}
        System.out.println(responseEntity.toString());
    }
}
