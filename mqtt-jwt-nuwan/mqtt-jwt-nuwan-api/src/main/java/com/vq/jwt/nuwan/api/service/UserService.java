/**
 * 
 */
package com.vq.jwt.nuwan.api.service;

import com.vq.jwt.nuwan.api.domain.UserDomain;

/**
 * @author Nuwan Dissanayaka
 *
 */
public interface UserService {
	
	/**
	 */
	public UserDomain loadUserByEmail(String email);
	
	public Boolean isUserExist(UserDomain user);

}
