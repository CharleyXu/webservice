package com.xu.webservice.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 * @author charlie Created on 2018/3/6.
 *
 * bean to xml  测试类
 */
@JacksonXmlRootElement(localName = "person")
public class Person {
	private String name;
	private Integer age;
	private String phone;
	@JacksonXmlElementWrapper(localName = "friends")
	@JacksonXmlProperty(localName = "friend")
	private List<String> friends;

	public Person() {
	}

	public Person(String name, Integer age, String phone, List<String> friends) {
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.friends = friends;
	}

	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Person setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Person setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public List<String> getFriends() {
		return friends;
	}

	public Person setFriends(List<String> friends) {
		this.friends = friends;
		return this;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", phone='" + phone + '\'' +
				", friends=" + friends +
				'}';
	}
}
