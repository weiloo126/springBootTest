package com.example.springBootTest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *   
 * @date 2017年9月19日
 */
public interface AuthorityMapper {

	@Select("SELECT distinct(a.name) FROM authority a LEFT JOIN role_authority b ON a.id = b.authority_id WHERE b.role_id = #{roleId}")
	List<String> getAuthorityNamesByRoleId(@Param("roleId") int roleId);
}
