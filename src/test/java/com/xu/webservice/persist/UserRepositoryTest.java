package com.xu.webservice.persist;

import com.xu.webservice.bean.User;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author charlie Created on 2018/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void UserTest() {
		//创建5条记录
		userRepository.save(new User().setUserName("u01").setAge(11));
		userRepository.save(new User().setUserName("u02").setAge(12));
		userRepository.save(new User().setUserName("u03").setAge(13));
		userRepository.save(new User().setUserName("u04").setAge(14));
		userRepository.save(new User().setUserName("u05").setAge(15));
		Assert.assertEquals(5, userRepository.findAll().size());
	}

	@Test
	public void findAllTest() {
		List<User> user_name_list = userRepository.findAll(Sort.by(Order.desc("userName")));
		System.out.println(user_name_list.size());

	}


}
