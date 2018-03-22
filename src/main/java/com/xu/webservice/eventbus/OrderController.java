package com.xu.webservice.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie Created on 2018/3/22.
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createOrder() {
		orderService.createOrder();
		return "create success";
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String changeOrder() {
		orderService.changerOrder();
		return "change success";
	}

}
