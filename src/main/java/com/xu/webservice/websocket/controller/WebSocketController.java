package com.xu.webservice.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuzhenchang Created on 2018/6/20.
 */
@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;    //Spring WebSocket消息发送模板

	//发送广播通知
	@MessageMapping("/addNotice")   //接收客户端发来的消息，客户端发送消息地址为：/app/addNotice
	@SendTo("/topic/notice")        //向客户端发送广播消息（方式一），客户端订阅消息地址为：/topic/notice
	public String notice(String notice) {
		System.out.println("notice:" + notice);
		//TODO 业务处理
		//向客户端发送广播消息（方式二），客户端订阅消息地址为：/topic/notice
//        messagingTemplate.convertAndSend("/topic/notice", msg);
		return "12344aca";
	}

	//发送点对点消息
	@MessageMapping("/msg")         //接收客户端发来的消息，客户端发送消息地址为：/app/msg
	@SendToUser("/queue/msg/result") //向当前发消息客户端（就是自己）发送消息的发送结果，客户端订阅消息地址为：/user/queue/msg/result
	public boolean sendMsg(String msg) {
		//TODO 业务处理
		//向指定客户端发送消息，第一个参数Principal.name为前面websocket握手认证通过的用户name（全局唯一的），客户端订阅消息地址为：/user/queue/msg/new
		messagingTemplate.convertAndSendToUser("1234", "/queue/msg/new", msg);
		return true;
	}

	@RequestMapping("/websocket/send")
	@ResponseBody
	public String sendMSG() {
		messagingTemplate.convertAndSend("/topic/notice", "32121");
		return "success";
	}
}
