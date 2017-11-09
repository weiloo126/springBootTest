package com.example.springBootTest.constant;

/**
 *   
 * @date 2017年8月30日
 */
public enum UserSexEnum {
	
	Man(0),
	
	Woman(1);
	
	private int val;
	
	UserSexEnum(int val){
		this.val = val;
	}
	
	public int getValue(){
		return this.val;
	}
}
