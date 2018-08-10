/**
 * 
 */
package com.vq.jwt.nuwan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vq.jwt.nuwan.api.dao.UserDao;
import com.vq.jwt.nuwan.api.domain.UserDomain;

/**
 * @author Nuwan Dissanayaka
 *
 */
@Repository
public interface UserRepository extends MongoRepository<UserDomain, Integer>,UserDao {


}
