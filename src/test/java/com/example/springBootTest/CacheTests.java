package com.example.springBootTest;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springBootTest.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CacheTests {
    
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
  
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private RedisTemplate<String, User> objectRedisTemplate;
    
//    @Test
//    public void test(){
//    	stringRedisTemplate.opsForValue().set("aaa", "111");
//    	Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
//    }
    
    @Test
    public void testObj() throws InterruptedException{
        User user = new User();
        user.setUsername("aa@126.com");
        user.setPassword("123");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.sample.user", user);
        operations.set("com.sample.user.time", user, 1, TimeUnit.SECONDS);
        boolean exists = redisTemplate.hasKey("com.sample.user.time");
        if(exists){
        	System.out.println("exists is true");
        }else{
        	System.out.println("exists is false");
        }
        Thread.sleep(1000);
        //redisTemplate.delete("com.sample.user.time");
        exists = redisTemplate.hasKey("com.sample.user.time");
        if(exists){
        	System.out.println("exists is true");
        }else{
        	System.out.println("exists is false");
        }
       // Assert.assertEquals("aa", operations.get("com.neo.f").getUsername());
    }
}
