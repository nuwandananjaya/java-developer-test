package com.vq.jwt.nuwan.api.config.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Nuwan Dissanayaka
 *
 */

@Component
public class JmsPublisher {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${auth.nuwan.activemq.topic}")
	String topic;
	
	/**
	 * JMS msg sender
	 * @param value
	 */
	public void send(String value){
		jmsTemplate.convertAndSend(topic, value);
	}

}
