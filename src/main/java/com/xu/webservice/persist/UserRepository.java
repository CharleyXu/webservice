package com.xu.webservice.persist;

import com.xu.webservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/16.
 */
@Component
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String user_name);

	User findByUserNameAndAge(String user_name, Integer age);


}
