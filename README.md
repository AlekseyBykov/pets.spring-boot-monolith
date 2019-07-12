# spring-boot-example
An example of Spring Boot application: secured RESTful endpoints and separate CRUD client. Component provides some business functions as services over the web. These services can be accessed through the URL.

Includes: 

* Secured endpoints by using Basic Authentication
* Springfox Swagger for generating the RESTful contract for consumers
* Actuator for system monitoring
* HAL Browser for visualization Actuator's data
* Separate client for consuming secured endpoints
* Configured Logback for store log messages and archiving old log files
* Glogal exceptions handler

For testing in Fiddler (or any other web debugging tool):

request method | request | headers | request body | response |
------------ | -------------| -------------| -------------| -------------|
`POST` | `http://localhost:8080/application/person/add` | `User-Agent: Fiddler Content-type: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | `{ "id":1, "firstName":"A", "lastName":"B" }` | `{"statusCode":"OK","message":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |
`GET`  | `http://localhost:8080/application/person/get/1` | `User-Agent: Fiddler Accept: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | | `{"statusCode":"OK","message":"","result":{"id":1,"firstName":"A","lastName":"B"}}` |
`PUT`  | `http://localhost:8080/application/person/update` | `User-Agent: Fiddler Content-type: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | `{ "id":1, "firstName":"C", "lastName":"D" }` | `{"statusCode":"OK","message":"","result":{"id":1,"firstName":"C","lastName":"D"}}` |
`DELETE` | `http://localhost:8080/application/person/delete/1` | `User-Agent: Fiddler Accept: application/json Authorization: Basic dXNlcjp1c2Vy Host: localhost:8080` | | `{"statusCode":"OK","message":"","result":[]}` |   
