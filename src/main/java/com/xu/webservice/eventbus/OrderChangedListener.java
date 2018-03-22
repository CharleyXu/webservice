package com.xu.webservice.eventbus;

import com.google.common.base.Stopwatch;
import com.google.common.eventbus.Subscribe;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/22.
 *
 * 事件监听器
 */
@Component
@EventBusListener
public class OrderChangedListener {

	@Subscribe
	public void created(OrderCreatedEvent event) throws InterruptedException {
		Stopwatch started = Stopwatch.createStarted();
		Long orderId = event.getOrderId();
		Long userId = event.getUserId();
		//创建订单成功后的逻辑处理，发短信、发邮件等
		System.out.printf("创建订单 %d , %d", event.getOrderId(), event.getUserId());
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" 耗时:" + started.elapsed(TimeUnit.SECONDS));
	}

	@Subscribe
	public void change(OrderChangeEvent event) throws InterruptedException {
		Stopwatch started = Stopwatch.createStarted();
		//处理订单变化后的修改，发送邮件提醒、更新物流等
		System.out.printf("订单变化 %d , %d", event.getOrderId(), event.getUserId() + "\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" 耗时:" + started.elapsed(TimeUnit.SECONDS));
	}
}
