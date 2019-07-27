# spring-boot-monolith
An example of Spring Boot monolith application: secured RESTful endpoints and separate command-line CRUD client. Monolith component provides some business functions as services over the web. These services can be accessed through the URL from independent deployable Web UI, mobile application, polyglot clients, etc.

Represents the Application tier(known as *backend*) in three-tier design. Typical layered architecture: API calls is processed from the Controller layer to the Persistence layer through the Service layer.

The component can be represented as a *microservice*(should be amended accordingly for using in event-driven architecture).

Includes: 

* Secured endpoints by using Basic Authentication
* Springfox Swagger for generating the RESTful contract for consumers
* Actuator for system monitoring
* HAL Browser for visualization Actuator's data
* Separate client for consuming secured endpoints
* Conversions between persistence-backed POJOs and DTOs by using MapStruct and manually
* Pagination by using Spring Data JPA
* Configured Logback for store log messages and archiving old log files
* Glogal exceptions handler
* Integration and unit tests

For testing in Fiddler (or any other web debugging tool):

request method | request | headers | request body | response |
------------ | -------------| -------------| -------------| -------------|
**POST** | `http://localhost:8080/application/person/add` | User-Agent: Fiddler<br>Content-type: application/json<br>Authorization: Basic<br>dXNlcjp1c2Vy<br>Host: localhost:8080 | `{ "id":1, "firstName":"A", "lastName":"B" }` | `{"statusCode":"OK","message":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |
**GET**  | `http://localhost:8080/application/person/get/1` | User-Agent: Fiddler<br>Accept: application/json<br>Authorization: Basic dXNlcjp1c2Vy<br>Host: localhost:8080 | | `{"statusCode":"OK","message":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |
**PUT**  | `http://localhost:8080/application/person/update` | User-Agent: Fiddler<br>Content-type: application/json<br>Authorization: Basic dXNlcjp1c2Vy<br>Host: localhost:8080 | `{ "id":1, "firstName":"C", "lastName":"D" }` | `{"statusCode":"OK","message":"","result":{"id":1,"firstName":"C","lastName":"D"}}` |
**DELETE** | `http://localhost:8080/application/person/delete/1` | User-Agent: Fiddler<br>Accept: application/json<br>Authorization: Basic dXNlcjp1c2Vy<br>Host: localhost:8080 | | `{"statusCode":"OK","message":"","result":[]}` |   
