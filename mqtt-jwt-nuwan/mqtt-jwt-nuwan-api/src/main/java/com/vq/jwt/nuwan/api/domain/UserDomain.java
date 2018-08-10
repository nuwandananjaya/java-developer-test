/**
 * 
 */
package com.vq.jwt.nuwan.api.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Nuwan Dissanayaka
 *
 */

@Document(collection = "users")
public class UserDomain implements Serializable{
	
	
	
  	/**
	 * 
	 */
	private static final long serialVersionUID = -3717463007684787264L;
	
	@Id
	@Field(value="id")
  	private Long id;
	
	@Field(value="email")
    private String email;
	
	@Field(value="password")
    private String password;
    
    public UserDomain() {
    	
    }
    
    public UserDomain(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

  

}
