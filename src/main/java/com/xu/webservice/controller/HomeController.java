package com.xu.webservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie Created on 2018/3/6.
 */
@RestController
public class HomeController {

	@RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE)
	public String home(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<!DOCTYPE html><html><head>");
		stringBuffer.append("<meta charset=\"UTF-8\"><title>Project Home</title></head>");
		stringBuffer.append("<body>");
		stringBuffer.append("REST API:<a href=\"swagger-ui.html\" target=\"_blank\">swagger-ui</a> <br/>");
		stringBuffer.append("</body></html>");
		return stringBuffer.toString();
	}
}
