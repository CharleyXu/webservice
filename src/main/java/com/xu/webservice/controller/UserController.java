package com.xu.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie Created on 2018/3/6.
 */
@RestController
@RequestMapping("/rest")
public class UserController {

	@RequestMapping(value = "/test01", method = RequestMethod.GET)
	public String getUser() {
		return "1233";
	}

}
