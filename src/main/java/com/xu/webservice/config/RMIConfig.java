package com.xu.webservice.config;

import com.xu.webservice.service.UserService;
import com.xu.webservice.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * @author charlie Created on 2018/3/6.
 */
@Configuration
public class RMIConfig {

	@Bean
	public RmiServiceExporter initRmiServiceExporter(){
		RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
		rmiServiceExporter.setServiceName("rmiService");
		rmiServiceExporter.setServicePort(1099);
		//-服务端暴露的接口
		rmiServiceExporter.setServiceInterface(UserService.class);
		rmiServiceExporter.setService(new UserServiceImpl());
		return rmiServiceExporter;
	}
}
