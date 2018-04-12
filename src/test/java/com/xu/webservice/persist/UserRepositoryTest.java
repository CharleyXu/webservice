package com.xu.webservice.persist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.xu.webservice.bean.Role;
import com.xu.webservice.bean.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
	@Autowired
	RoleRepository roleRepository;

	@Test
	public void roleTest() {
		roleRepository.save(new Role("1"));
		Assert.assertEquals(1, roleRepository.count());
	}

	@Test
	public void userTest() {

		Role role = new Role("1", "ok");
		roleRepository.save(role);
		//创建5条记录
		userRepository.save(new User().setUserName("u01").setAge(11).setRole(role));
		userRepository.save(new User().setUserName("u02").setAge(12).setRole(role));
		userRepository.save(new User().setUserName("u03").setAge(13).setRole(role));
		userRepository.save(new User().setUserName("u04").setAge(14).setRole(role));
		userRepository.save(new User().setUserName("u05").setAge(15).setRole(role));
		Assert.assertEquals(5, userRepository.findAll().size());
	}

	@Test
	public void findUser() {
		List<User> all = userRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String s = mapper.writeValueAsString(all);
			System.out.println("s:" + s);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

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


	public List<User> getStudent(String studentNumber, String name, String nickName,
			LocalDateTime birthday, String courseName, float chineseScore, float mathScore,
			float englishScore, float performancePoints) {
		Specification<User> specification = new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//用于暂时存放查询条件的集合
				List<Predicate> predicatesList = new ArrayList<>();
				//--------------------------------------------
				//查询条件示例
				//equal示例
				if (!Strings.isNullOrEmpty(name)) {
					Predicate namePredicate = cb.equal(root.get("name"), name);
					predicatesList.add(namePredicate);
				}
				//like示例
				if (!Strings.isNullOrEmpty(nickName)) {
					Predicate nickNamePredicate = cb.like(root.get("nickName"), '%' + nickName + '%');
					predicatesList.add(nickNamePredicate);
				}
				//between示例
				if (birthday != null) {
					Predicate birthdayPredicate = cb
							.between(root.get("birthday"), birthday, LocalDateTime.now());
					predicatesList.add(birthdayPredicate);
				}

				//关联表查询示例
				if (!Strings.isNullOrEmpty(courseName)) {
					Join<User, Role> joinTeacher = root.join("role", JoinType.LEFT);
					Predicate coursePredicate = cb.equal(joinTeacher.get("id"), courseName);
					predicatesList.add(coursePredicate);
				}

				//复杂条件组合示例
				if (chineseScore != 0 && mathScore != 0 && englishScore != 0 && performancePoints != 0) {
					Join<User, Role> joinExam = root.join("exams", JoinType.LEFT);
					Predicate predicateExamChinese = cb.ge(joinExam.get("chineseScore"), chineseScore);
					Predicate predicateExamMath = cb.ge(joinExam.get("mathScore"), mathScore);
					Predicate predicateExamEnglish = cb.ge(joinExam.get("englishScore"), englishScore);
					Predicate predicateExamPerformance = cb
							.ge(joinExam.get("performancePoints"), performancePoints);
					//组合
					Predicate predicateExam = cb
							.or(predicateExamChinese, predicateExamEnglish, predicateExamMath);
					Predicate predicateExamAll = cb.and(predicateExamPerformance, predicateExam);
					predicatesList.add(predicateExamAll);
				}
				//--------------------------------------------
				//排序示例(先根据学号排序，后根据姓名排序)
				query.orderBy(cb.asc(root.get("studentNumber")), cb.asc(root.get("name")));
				//--------------------------------------------
				//最终将查询条件拼好然后return
				Predicate[] predicates = new Predicate[predicatesList.size()];
				return cb.and(predicatesList.toArray(predicates));
			}


		};
		return userRepository.findAll(specification);
	}

}

