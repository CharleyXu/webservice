package com.xu.webservice.bean;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author charlie Created on 2018/3/6.
 *
 * 校验测试类 订单
 */
public class Order {

	@NotNull(message = "orderId不为Null")
	@Size(max = 10, min = 10)
	private String orderId;
	@NotEmpty(message = "customer不为空")
	private String customer;
	@NotNull(message = "email不为Null")
	@Email(message = "email格式错误")
	private String email;
	@NotEmpty(message = "address不为空")
	private String address;

	@NotNull(message = "IP格式不对")
	@Pattern(regexp = "^(((\\\\d{1,2})|(1\\\\d{2})|(2[2-4]\\\\d)|(25[0-5]))\\\\.){3}((\\\\d{1,2})|(1\\\\d{2})|(2[2-4]\\\\d)|(25[0-5]))$", message = "IP格式不对")
	private String ip;

	@NotNull(message = "status不为Null")
	private String status;
	@NotNull(message = "createDate不为Null")
	private Date createDate;

	public String getOrderId() {
		return orderId;
	}

	public Order setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	public String getCustomer() {
		return customer;
	}

	public Order setCustomer(String customer) {
		this.customer = customer;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Order setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Order setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Order setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Order setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public Order setIp(String ip) {
		this.ip = ip;
		return this;
	}
}
