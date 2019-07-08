package com.alekseybykov.examples.springboot.component.consumer;

import com.alekseybykov.examples.springboot.component.consumer.dto.PersonDTO;
import com.alekseybykov.examples.springboot.component.consumer.util.AuthUtil;
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
        RestClient restClient = new RestClient();
        restClient.createPerson();
        restClient.readPerson();
//        restClient.updatePerson();
//        restClient.deletePerson();
    }

    private void createPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/add");

        PersonDTO personDTO = PersonDTO.builder()
                .id(1L)
                .firstName("A")
                .lastName("B")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, AuthUtil.createEntityWithBasicAuth(
                        personDTO, MediaType.ALL, username, password), String.class);

        // Should print 'Response 201 CREATED'
        System.out.println(responseEntity.toString());
    }

    private void readPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/get/1");

        ResponseEntity<PersonDTO> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, AuthUtil.createEntityWithBasicAuth(
                        null, MediaType.ALL, username, password), PersonDTO.class);

        // Should print 'Response 200 OK' and person details
        System.out.println(responseEntity.toString());
    }

//    private void updatePerson() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/person/update";
//
//        Person person = new Person();
//        person.setId(1L);
//        person.setFirstName("C");
//        person.setLastName("D");
//
//        HttpEntity<Person> requestEntity = new HttpEntity<>(person, headers);
//        restTemplate.put(url, requestEntity);
//    }
//
//    private void deletePerson() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/person/delete/{id}";
//
//        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
//        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1L);
//
//    }
}
