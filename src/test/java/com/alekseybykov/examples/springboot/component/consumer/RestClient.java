package com.alekseybykov.examples.springboot.component.consumer;

import com.alekseybykov.examples.springboot.component.consumer.dto.PersonDTO;
import com.alekseybykov.examples.springboot.component.consumer.util.AuthUtil;
import com.alekseybykov.examples.springboot.component.entities.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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
        restClient.addPerson();
//        restClient.updatePerson();
//        restClient.deletePerson();
    }

    private void addPerson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s", contextPath, "person/add");

        PersonDTO personDTO = PersonDTO.builder()
                .id(1L)
                .firstName("A")
                .lastName("B")
                .build();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST,
                AuthUtil.createEntityWithBasicAuth(personDTO, MediaType.ALL, username, password), String.class);

        // Should print 'Response 201 CREATED'
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
