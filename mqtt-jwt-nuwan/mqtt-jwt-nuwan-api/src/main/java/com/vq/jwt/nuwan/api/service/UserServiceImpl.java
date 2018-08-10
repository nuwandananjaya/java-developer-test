/**
 * 
 */
package com.vq.jwt.nuwan.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vq.jwt.nuwan.api.dao.UserDao;
import com.vq.jwt.nuwan.api.domain.UserDomain;
import com.vq.jwt.nuwan.api.util.BouncyCastleMD5HashGenerator;

/**
 * @author Nuwan Dissanayaka
 *
 */
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	/**
	 * Load user by email
	 */
	@Override
	public UserDomain loadUserByEmail(String email) {
		UserDomain userDomain = userDao.loadUserByEmail(email);
		return userDomain;
	}

	/**
	 * Check user is exist
	 */
	@Override
	public Boolean isUserExist(UserDomain user) {
		Boolean isExist = false;
		if(null != user && null != user.getEmail() && null != user.getPassword()) {
			UserDomain userDomain = userDao.loadUserByEmail(user.getEmail());
			if(null != userDomain && null != userDomain.getPassword()) {
				BouncyCastleMD5HashGenerator bouncyCastleMD5HashGenerator = BouncyCastleMD5HashGenerator.getInstance();
				String hashPassword = bouncyCastleMD5HashGenerator.md5HashGenerator(user.getPassword());
				isExist = hashPassword.equals(userDomain.getPassword());
			}
		}
		return isExist;
	}

}
