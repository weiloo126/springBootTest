package com.example.springBootTest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.springBootTest.domain.User;

/**
 *   
 * @date 2017年8月30日
 */
//@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user")
	@Results({
		@Result(property = "sex",  column = "sex"),
		@Result(property = "username", column = "username")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	@Results({
		@Result(property = "sex",  column = "sex"),
		@Result(property = "username", column = "username")
	})
	User getOne(Long id);
	
	@Select("SELECT * FROM user WHERE username = #{username}")
	@Results({
		@Result(property = "sex",  column = "sex"),
		@Result(property = "username", column = "username")
	})
	User getByUsername(String username);

	@Insert("INSERT INTO user(username,password,sex) VALUES(#{username}, #{password}, #{sex})")
	void insert(User user);

	@Update("UPDATE user SET username = #{username} WHERE id =#{id}")
	void update(User user);

	@Delete("DELETE FROM user WHERE id = #{id}")
	void delete(@Param("id") Long id);
}
