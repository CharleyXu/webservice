package com.xu.webservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author charlie Created on 2018/3/9.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * 将 classpath:/static/ 目录下的内容映射到 / 路径下
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/");
	}
}
