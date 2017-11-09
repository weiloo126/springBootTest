package com.example.springBootTest.domain;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final Long serialVersionUID = 6114911700432495449L;
	/* 自增主键 */
	private Integer id;
	/* 角色名称 */
	private String name;
	/* 更新时间 */
	private String description;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
}