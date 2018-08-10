/**
 * 
 */
package com.vq.jwt.nuwan.api.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Nuwan Dissanayaka
 *
 */
public class DecodeJWT {
	
	Logger logger = LogManager.getLogger(DecodeJWT.class);
	
	private static DecodeJWT instance = new DecodeJWT();
	
	
	private DecodeJWT() {
		
	}
	
	public static DecodeJWT getInstance() {
		
		if(null == instance) {
			synchronized (DecodeJWT.class) {
				instance = new DecodeJWT();
			}
		}
		
		return instance;
	}
	
	/**
	 * JWT msg parser
	 * @param jwt
	 * @return String
	 */
	public String parseJWT(String jwt,String jwtSecretKey) {
		
		String subject = "";
		try {
		    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		 
		    Claims claims = Jwts.parser()         
						       .setSigningKey(signingKey)
						       .parseClaimsJws(jwt).getBody();
		    subject = claims.getSubject();
		}catch(Exception e) {
			logger.error("Failed [ "+jwt +" ]",e);
		}
	    
	    return subject;
	}

}
