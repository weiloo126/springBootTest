package com.example.springBootTest.mapper.test1;

import java.util.List;

import com.example.springBootTest.domain.User;

/**
 *   
 * @date 2017年9月6日
 */
public interface User1Mapper {

	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);
}
