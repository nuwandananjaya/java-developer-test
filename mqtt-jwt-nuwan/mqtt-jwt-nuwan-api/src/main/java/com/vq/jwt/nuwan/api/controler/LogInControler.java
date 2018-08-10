/**
 * 
 */
package com.vq.jwt.nuwan.api.controler;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vq.jwt.nuwan.api.config.jms.JmsPublisher;
import com.vq.jwt.nuwan.api.domain.Message;
import com.vq.jwt.nuwan.api.domain.UserDomain;
import com.vq.jwt.nuwan.api.repository.UserRepository;
import com.vq.jwt.nuwan.api.util.GenerateJWT;
import com.vq.jwt.nuwan.api.util.StatusDef;

/**
 * @author Nuwan Dissanayaka
 *
 */

@RestController
@RequestMapping("/api/v1")
public class LogInControler {
	
	 Logger logger = LogManager.getLogger(LogInControler.class);
	    

		@Autowired
		UserRepository userService; 
	    
		@Autowired
		JmsPublisher publisher;
		
		@Value("${jwt.nuwan.secret.key}")
		String jwtSecretKey;
		

		@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> authUser(@RequestBody UserDomain user) {
	        logger.info("authUser User : {}", user.getEmail());
	        
    		Message msg = null;
    		HttpStatus status = null;
	        
	        try {
	        	
		        if (userService.isUserExist(user)) {
		        	
		    		GenerateJWT gen = GenerateJWT.getInstance();
		    		String jwdStr =  gen.createJWT(UUID.randomUUID().toString(), user.getEmail(), 120000L,jwtSecretKey);
		    		publisher.send(jwdStr);
		    		
		    		msg = new Message();
		    		msg.setDetail(StatusDef.AUTHORIZED.getDecscription());
		    		
		    		status = HttpStatus.OK;
		        }else {
		        	msg = new Message();
		    		msg.setDetail(StatusDef.UNAUTHORIZED.getDecscription());
		    		
		    		status = HttpStatus.UNAUTHORIZED;
		        }
	        }catch(Exception e) {
	        	logger.error("authUser Exception Occoured : ", e);
	        }
	        
	        if(null == msg || null == status) {

	        	msg = new Message();
	    		msg.setDetail(StatusDef.SERVER_ERROR.getDecscription());
	    		
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        
            return new ResponseEntity<Message>(msg,status);
	 
	    }


}
