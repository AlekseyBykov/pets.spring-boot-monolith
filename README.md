# spring-boot-example
An example of Spring Boot application: secured RESTful endpoints and separate CRUD client.

Includes: 

* Secured endpoints by using Basic Authentication
* Springfox Swagger for generating the RESTful contract for consumers
* Actuator for system monitoring
* HAL Browser for visualization Actuator's data
* Separate client for consuming secured endpoints 

For testing in Fiddler:

request method | request | headers | request body | response |
------------ | -------------| -------------| -------------| -------------|
`POST` | `http://localhost:8080/application/person/add` | `User-Agent: Fiddler Content-type: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | `{ "id":1, "firstName":"A", "lastName":"B" }` | `{"errorCode":"NO_ERROR","errorMessage":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |   
