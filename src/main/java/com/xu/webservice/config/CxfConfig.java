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
//  @Bean
//  public ServletRegistrationBean dispatcherServlet(){
//    return new ServletRegistrationBean(new CXFServlet(),"/soap/*");
//  }
  @Autowired
  private Bus bus;

  @Autowired
  private UserService userService;

  @Bean
  public Endpoint endpoint(){
    EndpointImpl endpoint = new EndpointImpl(bus,userService);
    //wsdl文档路径  http://localhost:8081/services/user?wsdl
    endpoint.publish("/user");
    return endpoint;
  }

}
