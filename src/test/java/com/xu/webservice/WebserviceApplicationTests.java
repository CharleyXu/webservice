package com.xu.webservice;

import com.xu.webservice.bean.Order;
import com.xu.webservice.util.ValidateUtils;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.WebApplicationContextUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebserviceApplicationTests {
	@Autowired
	private ValidateUtils validateUtils;

	@Test
	public void contextLoads() {
		Order order = new Order();
		order.setOrderId("123a213343");
//		order.setEmail("12@qq.com");
		order.setAddress("a");
		order.setCreateDate(new Date());
		order.setCustomer("xiaohu");
		order.setStatus("1");
		System.out.println(validateUtils.validate(order));

//		WebApplicationContextUtils.getWebApplicationContext();
//		ApplicationContext ac = new FileSystemXmlApplicationContext("applicationContext.xml");
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
	}

	@Test
	public void contextTest() {
//		new FileSystemXmlApplicationContext("")
	}

}
