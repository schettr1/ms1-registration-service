# MICROSERVICES -
Microservices are small independent services that communicate over well-defined APIs. 
Because these services can be run independently, even if one service goes down, other services keep running. 
It provides loose coupling and better scalability. This is a very simple application. The microservices components are as follows - <br></br>

---
## Components
``ms1_config_server`` -  Spring Cloud Config server provides a central repository for configuration properties that are needed by applications in distributed system.<br></br>
``ms1_eureka_discovery`` -  Eureka server is where all services are registered to maintain seamless communication between services <br></br>
``ms1_gateway_api`` -  Gateway API is the front door for all requests coming from devices and websites into the application.<br></br>
``ms1_registration_service`` -   This service will register new employee and find employee. <br></br>
``ms1_payment_service`` -   This service will store payment details to the database. <br></br>
``ms1_email_service`` -   This service will store email information to the database. <br></br>

---

## Pre-requisite
Kafka Servers (both Zookeeper and Kafka) must be started and running before Kafka producer and consumer microservices are run.

---

## Process Flow 
Send request to GATEWAY-API using Postman. <br></br>
GATEWAY-API forwards the request to REGISTRATION-SERVICE based on the configured routes. <br></br>
First, REGISTRATION-SERVICE stores the user data to the database. <br></br>
Second, REGISTRATION-SERVICE sends payment details (Java Object) to PAYMENT-SERVICE using RabbitMQ. (``asynchronous``). 
PAYMENT-SERVICE listens/subscribes to the message (Json String) from RabbitMQ, de-serializes it to Java Object and stores it to the database. <br></br>
Third, REGISTRATION-SERVICE sends email information (Java Object) to EMAIL-SERVICE using Apache Kafka. (``asynchronous``).
Alternately, you can use Feign Client to send email to EMAIL-SERVICE. (``synchronous``). 
EMAIL-SERVICE listens/subscribes to the message from Apache Kafka, de-serializes the message (JSON String) to Java Object and stores it to the database. <br></br>

--- 

## Steps 
1. Start config server and go to URL [http://localhost:8888/config-server/default](http://localhost:8888/config-server/default). The page should load information from git repository. <br></br>
2. Start Discovery Eureka Server and monitor run status or below services at URL [http://localhost:8083/](http://localhost:8083/) <br></br>
3. Start Gateway_API. <br></br>
4. Start Zookeeper and Kafka servers in following order. Kafka server by default runs on port 2181. RabbitMQ does not require to startup server separately.<br></br>
<ul>
<li><i>C:\Users\suny4\apache-kafka_2.12-2.7.0>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties </i></li>      
<li><i>C:\Users\suny4\apache-kafka_2.12-2.7.0>.\bin\windows\kafka-server-start.bat .\config\server.properties </i></li>
</ul>      

5. Then start Registration, Email and Payment Services. <br></br>
6. Testing API using POSTMAN. <br></br>
<ul>
  <li>URL: http://localhost:8081/register/employees </li>   
  <li>Method: POST </li>
  <li>BODY: </li>
  <li><i>{ 
  "firstname" : "Surya", 
  "lastname" : "Chettri", 
  "email" : "test@email.com" 
  }</i></li>
  <li>You should see new records created in database tables EMPLOYEE, EMAIL and PAYMENT.</li>
</ul> 



--- 
