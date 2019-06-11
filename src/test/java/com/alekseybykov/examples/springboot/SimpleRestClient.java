package com.alekseybykov.examples.springboot;

import com.alekseybykov.examples.springboot.entities.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SimpleRestClient {

    public static void main(String args[]) {
        SimpleRestClient client = new SimpleRestClient();
        client.addPerson();
        client.updatePerson();
        client.getPersonById();
        client.deletePerson();
    }

    private void addPerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/person/add";

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
        String url = "http://localhost:8080/person/update";

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("C");
        person.setLastName("D");

        HttpEntity<Person> requestEntity = new HttpEntity<>(person, headers);
        restTemplate.put(url, requestEntity);
    }

    private void getPersonById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/person/get/{id}";

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Person.class, 1L);

        Person person = responseEntity.getBody();
        System.out.println(person);
    }

    private void deletePerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/person/delete/{id}";

        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1L);
    }
}