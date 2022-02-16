package com.sbc.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Create Publisher Channel to send messages using RabbitMQ.
 * RabbitMQ is considered non-blocking as it sends message 
 * and does not wait for the response. Whenever receiver is 
 * online, it receives the message. Multiple subscriber can 
 * receive messages.
 * @author suny4 02/27/21
 *
 */
public interface PublisherChannel {

	@Output("employeePaymentChannel")
	MessageChannel employeePayment();
}
