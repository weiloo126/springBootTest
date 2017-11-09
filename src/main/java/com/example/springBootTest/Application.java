package com.example.springBootTest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient //启用服务注册与发现
@MapperScan(value = "com.example.springBootTest.mapper")
public class Application {	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}