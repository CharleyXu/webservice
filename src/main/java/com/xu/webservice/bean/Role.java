package com.xu.webservice.bean;

import java.time.LocalDateTime;
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
 * @author CharleyXu Created on 2018/4/12.
 */
@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleName, String status) {
		this.roleName = roleName;
		this.status = status;
	}

	/**
	 * 主键 + 自增
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long roleId;
	@Column(name = "role_name", nullable = false)
	private String roleName;
	@Column(name = "role_status")
	private String status;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@CreatedDate
	private LocalDateTime createDate;
	/**
	 * 修改时间
	 */
	@Column(name = "last_modified_time")
	@LastModifiedDate
	private LocalDateTime lastModifiedTime;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", status='" + status + '\'' +
				", createDate=" + createDate +
				", lastModifiedTime=" + lastModifiedTime +
				'}';
	}
}
