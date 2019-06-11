package com.alekseybykov.examples.springboot;

import com.alekseybykov.examples.springboot.entities.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SimpleRestClient {

    public static void main(String args[]) {
        SimpleRestClient client = new SimpleRestClient();
        client.addUser();
    }

    private void addUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/person/add";

        Person person = new Person();
        person.setFirstName("A");
        person.setLastName("B");

        HttpEntity<Person> requestEntity = new HttpEntity<>(person, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);

        System.out.println(uri.getPath());
    }
}