# MICROSERVICES -
Microservices are small independent services that communicate over well-defined APIs. 
Because these services can be run independently, even if one service goes down, other services keep running. 
It provides loose coupling and better scalability. <br></br>

---
## Components
```bash
ms1_config_server
ms1_eureka_discovery
ms1_gateway_api
ms1_registration_service
ms1_payment_service
ms1_email_service
```
---

## Pre-requisite
Kafka Servers (both Zookeeper and Kafka) must be started and running before Kafka producer and consumer microservices are run.

---

## Data Flow
UI-SERVICE (_Postman_) sends request to GATEWAY-API. <br></br>
GATEWAY-API is the entry point to the microservices application. <br></br>
GATEWAY-API forwards the request to REGISTRATION-SERVICE based on the routes configured in _GatewayConfiguration.class_ <br></br>
First, REGISTRATION-SERVICE stores the user data to the database. <br></br>
Second, REGISTRATION-SERVICE sends payment (Java Object) to PAYMENT-SERVICE using RabbitMQ. (``asynchronous``). 
PAYMENT-SERVICE listens/subscribes to the message (Json String) from RabbitMQ, de-serializes it to Java Object and stores it to the database. <br></br>
Third, REGISTRATION-SERVICE sends email  (Java Object) to EMAIL-SERVICE using Apache Kafka. (``asynchronous``).
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
<li>POST: [http://localhost:8081/register/employees](http://localhost:8081/register/employees) </li>      
<li>BODY: </li>
<i>{ 
"firstname" : "Surya", 
"lastname" : "Chettri", 
"email" : "schetttri@gmail.com" 
}</i>
</ul> 

--- 
