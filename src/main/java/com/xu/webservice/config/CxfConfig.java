package com.xu.webservice.config;

import com.xu.webservice.service.UserService;
import com.xu.webservice.service.UserServiceImpl;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CharleyXu Created on 2018/3/6.
 * 基于cxf的配置类
 */
@Configuration
public class CxfConfig {
  @Bean
  public ServletRegistrationBean dispatcherServlet(){
    return new ServletRegistrationBean(new CXFServlet(),"/soap/*");
  }

  @Bean(name = Bus.DEFAULT_BUS_ID)
  public SpringBus springBus(){
    return new SpringBus();
  }

  @Bean
  public UserService userService(){
    return new UserServiceImpl();
  }

  @Bean
  public Endpoint endpoint(){
    EndpointImpl endpoint = new EndpointImpl(springBus(),userService());
    endpoint.publish("/user");
    return endpoint;
  }

}
