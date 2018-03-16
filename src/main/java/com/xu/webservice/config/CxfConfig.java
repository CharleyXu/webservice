package com.xu.webservice.config;

import com.xu.webservice.service.UserService;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CharleyXu Created on 2018/3/6.
 * 基于cxf的配置类
 */
@Configuration
public class CxfConfig {

  @Autowired
  private Bus bus;

  @Autowired
  private UserService userService;

  @Bean
  public Endpoint endpoint(){
		//wsdl文档路径  http://localhost:8081/services/userService?wsdl
		EndpointImpl endpoint = new EndpointImpl(bus,userService);
		endpoint.publish("/userService");
		return endpoint;
  }

}
