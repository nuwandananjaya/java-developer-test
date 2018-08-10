/**
 * 
 */
package com.vq.jwt.nuwan.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.vq.jwt.nuwan.api.domain.UserDomain;
import com.vq.jwt.nuwan.api.util.BouncyCastleMD5HashGenerator;

/**
 * @author Nuwan Dissanayaka
 *
 */
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public UserDomain loadUserByEmail(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		return mongoTemplate.findOne(query, UserDomain.class,"users");
	}

	@Override
	public Boolean isUserExist(UserDomain user) {
		Boolean isExist = false;
		if(null != user && null != user.getEmail() && null != user.getPassword()) {
			mongoTemplate.findAll(UserDomain.class,"users");
			UserDomain userDomain = loadUserByEmail(user.getEmail());
			if(null != userDomain && null != userDomain.getPassword()) {
				BouncyCastleMD5HashGenerator bouncyCastleMD5HashGenerator = BouncyCastleMD5HashGenerator.getInstance();
				String hashPassword = bouncyCastleMD5HashGenerator.md5HashGenerator(user.getPassword());
				isExist = hashPassword.equals(userDomain.getPassword());
			}
		}
		return isExist;
	}

}
