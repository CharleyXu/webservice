package com.xu.webservice.bean;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Test;

/**
 * @author charlie Created on 2018/3/6.
 */
public class OrderTest {
	@Test
	public void orderTest(){
		Order order = new Order();
		order.setOrderId("123a213343");
		order.setEmail("12@qq.com");
		order.setAddress("a");
		order.setCreateDate(new Date());
		order.setCustomer("xiaohu");
		order.setStatus("1");
//		order.setIp("");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Order>> validate = validator.validate(order);
		Iterator<ConstraintViolation<Order>> iterator = validate.iterator();
		System.out.println("size:"+validate.size());
		while (iterator.hasNext()){
			ConstraintViolation<Order> violation = iterator.next();
			System.out.println(violation.getPropertyPath()+"  "+violation.getMessage());
			//throw new ValidationException(violation.getMessage());
		}
	}
}
