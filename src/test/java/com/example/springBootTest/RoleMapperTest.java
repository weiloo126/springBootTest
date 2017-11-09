package com.example.springBootTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springBootTest.mapper.AuthorityMapper;
import com.example.springBootTest.mapper.RoleMapper;

/**
 *   
 * @date 2017年9月19日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Test
	public void test(){
		System.out.println(roleMapper.getRolesByUserId(18));
		System.out.println(authorityMapper.getAuthorityNamesByRoleId(1));
	}
}
