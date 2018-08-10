/**
 * 
 */
package com.vq.jwt.nuwan.api.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Nuwan Dissanayaka
 *
 */
@Component
public class GenerateJWT {
	
	Logger logger = LogManager.getLogger(GenerateJWT.class);
	
	private static GenerateJWT instance = new GenerateJWT();
	
	@Value("${jwt.nuwan.secret.key}")
	String jwtSecretKey;
	
	private GenerateJWT() {
		
	}
	
	public static GenerateJWT getInstance() {
		
		if(null == instance) {
			synchronized (GenerateJWT.class) {
				instance = new GenerateJWT();
			}
		}
		
		return instance;
	}
		

	/**
	 *  JWT String generator
	 * @param id
	 * @param issuer
	 * @param subject
	 * @param ttlMillis
	 * @return String 
	 */
	public String createJWT(String id, String subject, long ttlMillis,String jwtSecretKey) {
		
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .signWith(signingKey);
	 
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    return builder.compact();
	}
}
