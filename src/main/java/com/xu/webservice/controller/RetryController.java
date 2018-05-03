package com.xu.webservice.controller;

import com.xu.webservice.util.RetryUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CharleyXu Created on 2018/5/3.
 */
@RestController
@Api(tags = {"失败重试机制"})
@RequestMapping("/rest")
public class RetryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryController.class);

	@ApiOperation(value = "失败重试")
	@RequestMapping(value = "/retry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String retry() {
		// here shows thress kinds of test case
		Callable<Integer> task = () -> {
			int a = 1 / 0;
			return 2;
		};
		//预期值重试
		Integer result = RetryUtils.retry(task, 10L, 5L, TimeUnit.SECONDS, 3);
		LOGGER.info("result: {}", result);
		return result.toString();
	}

}
