package com.xu.webservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie Created on 2018/3/6.
 */
@RestController
@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
public class HomeController {

	@RequestMapping
	public String home() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<!DOCTYPE html>");
		stringBuffer.append("<html><head>");
		stringBuffer.append("<meta charset=\"UTF-8\">");
		stringBuffer.append("<title>Home Api!</title>");
		stringBuffer.append("</head><body>");
		stringBuffer
				.append("<p>REST API:<a href=\"swagger-ui.html\" target=\"_blank\">swagger-ui</a> </p>");
		stringBuffer.append("</body>");
		stringBuffer.append("</html>");

		return stringBuffer.toString();
	}
}
