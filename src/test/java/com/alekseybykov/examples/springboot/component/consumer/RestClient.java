package com.alekseybykov.examples.springboot.component.consumer;

import com.alekseybykov.examples.springboot.component.consumer.dto.PersonDTO;
import com.alekseybykov.examples.springboot.component.consumer.util.AuthUtil;
import com.google.common.base.Preconditions;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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
    }

    private void createPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/add");

        PersonDTO dto = PersonDTO.builder()
                .id(1L)
                .firstName("A")
                .lastName("B")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, AuthUtil.createEntityWithBasicAuth(
                        dto, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print 'Response 201 CREATED'
        System.out.println(responseEntity.toString());
    }

    private PersonDTO readPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/get/1");

        ResponseEntity<PersonDTO> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), PersonDTO.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print 'Response 200 OK' and person details
        System.out.println(responseEntity.toString());

        return responseEntity.getBody();
    }

    private void updatePerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/update");

        PersonDTO dto = readPerson();
        dto.setFirstName("C");
        dto.setLastName("D");

        ResponseEntity<PersonDTO> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, AuthUtil.createEntityWithBasicAuth(
                        dto, MediaType.ALL, username, password), PersonDTO.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print 'Response 200 OK' and updated person details
        System.out.println(responseEntity.toString());
    }

    private void deletePerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/delete/1");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.DELETE, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), String.class);
        Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful());

        // Should print 'Response 204 NO_CONTENT'
        System.out.println(responseEntity.toString());
    }
}