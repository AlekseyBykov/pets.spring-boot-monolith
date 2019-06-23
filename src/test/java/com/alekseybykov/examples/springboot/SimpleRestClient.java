package com.alekseybykov.examples.springboot;

import com.alekseybykov.examples.springboot.component.entities.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class SimpleRestClient {

    private static final String uri = "http://localhost:8080/person";

    public static void main(String args[]) {
        SimpleRestClient client = new SimpleRestClient();
        client.addPerson();
        client.getPersonByIdByUsingRequestParam();
        client.updatePerson();
        client.getPersonByIdByUsingPathParam();
//        client.deletePerson();
    }

    private void addPerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s%s", uri, "/add");

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("A");
        person.setLastName("B");

        HttpEntity<Person> requestEntity = new HttpEntity<>(person, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);

        System.out.println(uri.getPath());
    }

    private void updatePerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s%s", uri, "/update");

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("C");
        person.setLastName("D");

        HttpEntity<Person> requestEntity = new HttpEntity<>(person, headers);
        restTemplate.put(url, requestEntity);
    }

    private void getPersonByIdByUsingRequestParam() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = String.format("%s%s", uri, "/get");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("id", 1L);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                Person.class
        );

        Person person = responseEntity.getBody();
        System.out.println(person);
    }

    private void getPersonByIdByUsingPathParam() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = String.format("%s%s", uri, "/get/{id}");

        Map<String, Long> uriParams = new HashMap<>();
        uriParams.put("id", 1L);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                Person.class,
                uriParams
        );

        Person person = responseEntity.getBody();
        System.out.println(person);
    }

    private void deletePerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s%s", uri, "/delete/{id}");

        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1L);
    }
}