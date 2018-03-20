package com.xu.webservice.controller;

import com.google.common.base.Strings;
import com.xu.webservice.task.DynamicScheduleTask;
import com.xu.webservice.task.MyScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie Created on 2018/3/20.
 */
@RestController
public class DynamicScheduledTaskController {

	@Autowired
	private MyScheduler myScheduler;
	@Autowired
	private DynamicScheduleTask dynamicScheduleTask;

	@RequestMapping(value = "/task/update", method = RequestMethod.GET)
	public String updateDynamicScheduledTask(
			@RequestParam(defaultValue = "*/10 * * * * ?") String cronParam) {
		if (Strings.isNullOrEmpty(cronParam)) {
			System.out.println("cronParam is error !");
			cronParam = "*/3 * * * * ?";
		}
		myScheduler.setCron(cronParam);
		return "Success";
	}

	@RequestMapping(value = "/task/update2", method = RequestMethod.POST)
	public String updateDynamicScheduledTaskSecond(
			@RequestParam(defaultValue = "*/5 * * * * ?") String cronParam) {
		if (Strings.isNullOrEmpty(cronParam)) {
			System.out.println("cronParam is error !");
			cronParam = "*/3 * * * * ?";
		}
		dynamicScheduleTask.setCron(cronParam);
		return "Success2";
	}
}
