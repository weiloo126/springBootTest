package com.example.springBootTest.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootTest.domain.User;
import com.example.springBootTest.exception.MyException;
import com.example.springBootTest.mapper.test1.User1Mapper;

/**
 * RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
 *   
 * @date 2017年7月18日
 */
@RestController
public class HelloWorldController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	private User1Mapper user1Mapper;
	
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
    	logger.info("log test");
        return "Hello World! 你好！";
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @Cacheable(value = "hello")
    public User getUser() {
    	System.out.println("无缓存的时候调用");
    	User user = new User();
    	user.setUsername("小明");
    	user.setPassword("xxxx");
        return user;
    }

    @RequestMapping(value = "/getUser2", method = RequestMethod.GET)
    @Cacheable(value = "hello")
    public User getUser2() {
    	System.out.println("无缓存的时候调用");
    	User user = new User();
    	user.setUsername("小花");
    	user.setPassword("xxxx");
        return user;
    }
    
    /**
     * 添加测试方法获取sessionid
     * 登录redis 输入 keys '*sessions*'
     * @param session
     * @return
     */
	@ApiOperation(value = "缓存sessionid到redis", notes = "")
    @RequestMapping(value = "/uid", method = RequestMethod.GET)
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
    
	@ApiOperation(value="获取用户详细信息", notes="根据id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query")//必须定义paramType参数， paramType 有五个可选值： path, query, body, header, form
    @RequestMapping(value = "/getUserTest1", method = RequestMethod.GET)
    public com.example.springBootTest.domain.User getUserTest1(@RequestParam("id") Long id){
    	return user1Mapper.getOne(id);
    }	

    @RequestMapping("/jsonExHandle")
    public String jsonExHandle() throws MyException {
        throw new MyException(1001, "json exception handle sample");
    }
}
