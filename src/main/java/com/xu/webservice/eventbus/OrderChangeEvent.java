package com.xu.webservice.eventbus;

/**
 * @author charlie Created on 2018/3/22.
 */
public class OrderChangeEvent {

	private Long orderId;
	private Long userId;

	public OrderChangeEvent() {
	}

	public OrderChangeEvent(Long orderId, Long userId) {
		this.orderId = orderId;
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public OrderChangeEvent setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public OrderChangeEvent setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
}
