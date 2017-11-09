package com.example.springBootTest.domain;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class UserRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7231362262822547408L;
	/* 角色ID */
	private Integer roleId;
	/* OP用户ID */
	private Integer userId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
		
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
		
}