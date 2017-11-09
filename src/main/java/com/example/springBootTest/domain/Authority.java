package com.example.springBootTest.domain;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class Authority implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6703641774313421745L;
	/* 自增主键 */
	private Long id;
	/* 权限名称 */
	private String name;
	/* 更新时间 */
	private String description;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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