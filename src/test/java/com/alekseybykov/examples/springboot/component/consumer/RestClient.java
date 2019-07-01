package com.alekseybykov.examples.springboot.component.consumer;

import com.alekseybykov.examples.springboot.component.entities.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestClient {

    public static void main(String args[]) {
        RestClient restClient = new RestClient();
        restClient.addPerson();
        restClient.updatePerson();
        restClient.deletePerson();
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

    private void deletePerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/person/delete/{id}";

        HttpEntity<Person> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1L);

    }
}