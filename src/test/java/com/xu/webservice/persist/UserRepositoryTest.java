package com.xu.webservice.persist;

import com.xu.webservice.bean.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
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

	/**
	 * 不带查询条件
	 */
	@Test
	public void findAllNoCriteriaTest() {
		int page=1,size = 5;
		Sort sort = new Sort(Direction.DESC,"age","userName");
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<User> page1 = userRepository.findAll(pageable);
		System.out.println("12 "+page1.getSize()+"\n"+page1.getTotalElements());
	}

	@Test
	public void findAllCriteriaTest(){
		int age = 13;
		int page=1,size = 5;
		Sort sort = new Sort(Direction.DESC,"age","userName");
		Pageable pageable = PageRequest.of(page, size, sort);

		userRepository.findAll(

				new Specification<User>() {
			@Nullable
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate age1 = criteriaBuilder
						.greaterThanOrEqualTo(root.get("age").as(Integer.class), 13);
				Predicate userName2 = criteriaBuilder.like(root.get("userName").as(String.class), "02");
				criteriaQuery.where(criteriaBuilder.and(age1,userName2));
				return criteriaQuery.getRestriction();
			}
		}

		,pageable);

	}


}
