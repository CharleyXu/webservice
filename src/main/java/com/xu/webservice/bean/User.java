package com.xu.webservice.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author CharleyXu Created on 2018/3/5.
 * JPA实体类
 */
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

  private static final long serialVersionUID = 8830160143422999070L;

	/**
	 * 主键 + 自增
	 */
	@Id
	@GeneratedValue
	private Long userId;
	@Column(name = "user_name", nullable = false)
	private String userName;
	@Column
	private Integer age;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@CreatedDate
	private Date createDate;

	/**
	 * 修改时间
	 */
	@Column(name = "last_modified_time")
	@LastModifiedDate
	private Date lastModifiedTime;

	public Long getUserId() {
		return userId;
  }

	public User setUserId(Long userId) {
		this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public User setUserName(String userName) {
    this.userName = userName;
    return this;
  }

	public Integer getAge() {
		return age;
	}

	public User setAge(Integer age) {
		this.age = age;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public User setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public User setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
		return this;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", age=" + age +
				", createDate=" + createDate +
				", lastModifiedTime=" + lastModifiedTime +
				'}';
	}
}
