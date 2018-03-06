package com.xu.webservice;

import com.xu.webservice.bean.Order;
import com.xu.webservice.util.ValidateUtils;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

	}

}
