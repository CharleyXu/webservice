package com.xu.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author charlie Created on 2018/3/6.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * 指定扫描的包会生成文档,默认是显示所有接口,
	 *
	 * 可以用@ApiIgnore注解标识该接口不显示。
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("rest api")
				.apiInfo(apiInfo())
				.select()
				// 指定controller存放的目录路径
				.apis(RequestHandlerSelectors.basePackage("com.xu.webservice.controller"))
//				.paths(PathSelectors.ant("/rest/**"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 配置一些基本的信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 文档标题
				.title("REST API")
				// 文档描述
				.description("Interfaces Description")
				.version("v1")
				.build();
	}
}
