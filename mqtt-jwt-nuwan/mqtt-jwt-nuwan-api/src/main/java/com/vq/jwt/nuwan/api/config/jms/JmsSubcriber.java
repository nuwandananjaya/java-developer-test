package com.vq.jwt.nuwan.api.config.jms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.vq.jwt.nuwan.api.util.DecodeJWT;
import com.vq.jwt.nuwan.api.util.JWTKeyReformatter;

/**
 * @author Nuwan Dissanayaka
 *
 */

@Component
public class JmsSubcriber {
	
	Logger logger = LogManager.getLogger(JmsSubcriber.class);
	
	@Value("${jwt.nuwan.secret.key}")
	String jwtSecretKey;
	
	/**
	 * JMS msg receiver
	 * @param msg
	 */
	@JmsListener(destination = "${auth.nuwan.activemq.topic}")
	public void receive(String msg){
		JWTKeyReformatter jwtKeyReformatter = JWTKeyReformatter.getInstance();
		try {
			DecodeJWT decode = DecodeJWT.getInstance();
			decode.parseJWT(msg,jwtSecretKey);
			
			logger.debug("Sucess {} " , jwtKeyReformatter.formatter(msg));
		}catch(Exception e) {
			logger.error("Failed {}", jwtKeyReformatter.formatter(msg));
		}
	}
	
}
