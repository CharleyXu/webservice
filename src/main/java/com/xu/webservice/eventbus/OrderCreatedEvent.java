package com.xu.webservice.eventbus;

/**
 * @author charlie Created on 2018/3/22.
 *
 * 订单创建 事件
 */
public class OrderCreatedEvent {

	private Long orderId;
	private Long userId;

	public OrderCreatedEvent() {
	}

	public OrderCreatedEvent(Long orderId, Long userId) {
		this.orderId = orderId;
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public OrderCreatedEvent setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public OrderCreatedEvent setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
}
