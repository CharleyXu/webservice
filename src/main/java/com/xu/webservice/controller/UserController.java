package com.xu.webservice.controller;

import com.xu.webservice.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie Created on 2018/3/6.
 */
@Api(tags = {"模拟模块"})
@RestController
@RequestMapping("/rest")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "模拟方法 getUser")
	@RequestMapping(value = "/test01", method = RequestMethod.GET)
	public String getUser() {
		return "1233";
	}

	@ApiOperation(value = "模拟方法 setUser")
	@RequestMapping(value = "/test02", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User setUser(
			@ApiParam(value = "用户Id", defaultValue = "0000x")
			@RequestParam(name = "userId", required = true)
					Long userId,
			@ApiParam(value = "用户名称", defaultValue = "DefaultName")
			@RequestParam(name = "userName", required = true)
					String userName) {
		LOGGER.info(String.format("%d, %s", userId, userName));
		return new User().setUserId(userId).setUserName(userName);
	}

}
