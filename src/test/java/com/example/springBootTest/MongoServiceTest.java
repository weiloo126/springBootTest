package com.example.springBootTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springBootTest.domain.User;
import com.example.springBootTest.service.MongoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoServiceTest {

    @Autowired
    private MongoService mongoService;

    @Test
    public void testSaveUser() throws Exception {
        User user=new User();
        user.setId(2l);
        user.setUsername("小明");
        user.setPassword("fffooo123");
        mongoService.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
       User user = mongoService.findUserByUsername("小明");
       System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(2l);
        user.setUsername("天空");
        user.setPassword("fffxxxx");
        mongoService.updateUser(user);
    }

    @Test
    public void deleteUserById(){
    	mongoService.deleteUserById(1l);
    }

}