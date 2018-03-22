package com.xu.webservice.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.xu.webservice.util.SpringContextUtils;
import java.util.List;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/22.
 *
 * 事件注册中心	优雅地使用事件驱动进行代码解耦
 */
@Component
public class EventBusCenter {

	//管理同步事件
	private EventBus syncEventBus = new EventBus();
	//管理异步事件
	private EventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());

	public void postSync(Object event) {
		syncEventBus.post(event);
	}

	public void postAsync(Object event) {
		asyncEventBus.post(event);
	}

	@PostConstruct
	public void init() {
		//获取所有带有 @EventBusListener 的 Bean , 将它们注册为监听者
		List<Object> listeners = SpringContextUtils
				.getBeansWithAnnotation(EventBusListener.class);
		for (Object listener : listeners) {
			syncEventBus.register(listener);
			asyncEventBus.register(listener);
		}
	}

}
