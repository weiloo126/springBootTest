package com.example.springBootTest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.springBootTest.domain.Role;

/**
 *   
 * @date 2017年9月19日
 */
public interface RoleMapper {

	@Select("SELECT a.* from role a LEFT JOIN user_role b ON a.id = b.role_id WHERE b.user_id = #{userId}")
	List<Role> getRolesByUserId(@Param("userId") Integer userId);
}
