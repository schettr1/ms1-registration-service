package com.sbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbc.entity.Email;
import com.sbc.entity.Employee;
import com.sbc.entity.Payment;
import com.sbc.feign.FeignClientEmailService;
import com.sbc.rabbitmq.PublisherChannel;
import com.sbc.service.RegistrationService;

@RestController
@EnableBinding(PublisherChannel.class)
@RequestMapping(path="/register")
public class RegistrationController {

	@Autowired
	RegistrationService rservice;

	@Autowired
	private FeignClientEmailService fcemailService;
	
	@Autowired
	private PublisherChannel publisherChannel;
	
	static String KAFKA_TOPIC_NAME = "KafkaTopic1001";
	
	@Autowired
	private KafkaTemplate<String, Email> kafkaTemplate;
	
	
	@PostMapping(path="/employees", consumes="application/json")
	public void createEmployee(@RequestBody Employee employee) {
		
		// call RegistrationService
		Employee savedEmp = rservice.create(employee);
		
		// send payment using RabbitMQPaymentSource
		Payment payment = new Payment("Registration Fee", savedEmp.getId(), 250.00);
		publisherChannel.employeePayment().send(MessageBuilder.withPayload(payment).build());
		System.out.println("Payment = " + payment.toString());
		
		// send email using Apache Kafka asynchronously
		Email email = new Email("manager@gmail.com", employee.getEmail(), "You have been successfully registered. Thank you!");
		kafkaTemplate.send(KAFKA_TOPIC_NAME, email);
		System.out.println("Kafka message send!");
		
		/*
		 *** Alternately, send email using Feign-Client
		 * Email("manager@gmail.com", employee.getEmail(),
		 * "You have been successfully registered. Thank you!");
		 * fcemailService.sendEmail(email);
		 */
		
	}
	
	@GetMapping(path="/employees/{id}", produces="application/json")
	public Employee getEmployee(@PathVariable int id) {
		
		return rservice.getEmployee(id);	
	}
}
