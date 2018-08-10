/**
 * 
 */
package com.vq.jwt.nuwan.api.config.mongo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.vq.jwt.nuwan.api.domain.UserDomain;
import com.vq.jwt.nuwan.api.repository.UserRepository;


/**
 * @author Nuwan Dissanayaka
 *
 */

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {
	
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {
            userRepository.save(new UserDomain(1l, "nuwan@mail.com", "287bda5aa876b3fa7dbf8e6ec0175738"));
            userRepository.save(new UserDomain(2l, "sam@mail.com", "287bda5aa876b3fa7dbf8e6ec0175738"));
            userRepository.save(new UserDomain(3l, "admin@mail.com", "287bda5aa876b3fa7dbf8e6ec0175738"));
        };
}

}
