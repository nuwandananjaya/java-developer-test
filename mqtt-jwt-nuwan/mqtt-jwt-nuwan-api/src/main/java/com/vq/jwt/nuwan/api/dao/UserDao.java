/**
 * 
 */
package com.vq.jwt.nuwan.api.dao;

import com.vq.jwt.nuwan.api.domain.UserDomain;

/**
 * @author Nuwan Dissanayaka
 *
 */
public interface UserDao {
	
	public UserDomain loadUserByEmail(String email);
	
	public Boolean isUserExist(UserDomain user);

}
