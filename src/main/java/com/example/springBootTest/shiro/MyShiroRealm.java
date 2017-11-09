package com.example.springBootTest.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springBootTest.domain.Role;
import com.example.springBootTest.domain.User;
import com.example.springBootTest.mapper.AuthorityMapper;
import com.example.springBootTest.mapper.RoleMapper;
import com.example.springBootTest.mapper.UserMapper;

/**
 *   
 * @date 2017年9月19日
 */
@Component
public class MyShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//获取用户的输入的账号.
	    String username = (String)token.getPrincipal();
	    System.out.println(token.getCredentials());
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    User user = userMapper.getByUsername(username);
	    System.out.println("----->>user="+user);
	    if(user == null){
	        return null;
	    }
	    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	            user.getUsername(), //用户名
	            user.getPassword(), //密码	            
	            getName()  //realm name
	    );
	    return authenticationInfo;
	}

	/*
	 * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    User user  = (User) principals.getPrimaryPrincipal();
	    List<Role> roles = roleMapper.getRolesByUserId(user.getId().intValue());
	    Set<String> roleNames = new HashSet<>();
	    for(Role role: roles){
	    	roleNames.add(role.getName());
	    	authorizationInfo.addStringPermissions(authorityMapper.getAuthorityNamesByRoleId(role.getId()));	    	
	    }
	    authorizationInfo.setRoles(roleNames);
	    return authorizationInfo;
	}

}
