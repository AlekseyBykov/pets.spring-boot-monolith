# spring-boot-example
An example of Spring Boot application: secured RESTful endpoints and separate CRUD client.

Includes: 

* Secured endpoints by using Basic Authentication
* Springfox Swagger for generating the RESTful contract for consumers
* Actuator for system monitoring
* HAL Browser for visualization Actuator's data
* Separate client for consuming secured endpoints 

For testing in Fiddler (or any other web debugging tool):

request method | request | headers | request body | response |
------------ | -------------| -------------| -------------| -------------|
`POST` | `http://localhost:8080/application/person/add` | `User-Agent: Fiddler Content-type: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | `{ "id":1, "firstName":"A", "lastName":"B" }` | `{"errorCode":"NO_ERROR","errorMessage":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |
`GET`  | `http://localhost:8080/application/person/get/1` | `User-Agent: Fiddler Accept: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | | `{"errorCode":"NO_ERROR","errorMessage":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |
`PUT`  | `http://localhost:8080/application/person/update` | `User-Agent: Fiddler Content-type: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | `{ "id":1, "firstName":"C", "lastName":"D" }` | `{"errorCode":"NO_ERROR","errorMessage":"","result":{"id":1,"firstName":"C","lastName":"D"}}` |
`DELETE` | `http://localhost:8080/application/person/delete/1` | `User-Agent: Fiddler Accept: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | | `{"errorCode":"NO_ERROR","errorMessage":"","result":[]}` |   
