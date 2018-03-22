package com.xu.webservice.eventbus;

import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author charlie Created on 2018/3/22.
 *
 * 发送事件
 */
@Service
public class OrderService {

	@Autowired
	private EventBusCenter eventBusCenter;

	public void createOrder() {
		//处理创建订单。。。
		System.out.println("createOrder:" + LocalTime.now());
		//发送异步事件
		eventBusCenter.postAsync(new OrderCreatedEvent().setOrderId(111L).setUserId(222L));
		System.out.println("End:" + LocalTime.now());
	}

	public void changerOrder() {
		//处理订单变化后的修改
		System.out.println("changeOrder:" + LocalTime.now());
		//发送同步事件
		eventBusCenter.postSync(new OrderChangeEvent().setOrderId(111L).setUserId(222L));
		System.out.println("End：" + LocalTime.now());
	}

}
