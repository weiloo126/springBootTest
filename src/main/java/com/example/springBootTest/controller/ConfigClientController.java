package com.example.springBootTest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Config服务端提供配置服务，客户端获取配置参数
 * 进行一些小实验，手动修改sample-config-dev.properties中配置信息为：sample.hello=hello in dev update提交到github,再次在浏览器访问http://localhost:7000/sampleHello，返回：sample.hello: hello in dev，说明获取的信息还是旧的参数，这是为什么呢？因为spirngboot项目只有在启动的时候才会获取配置文件的值，修改github信息后，client端并没有再次去获取，所以导致这个问题。
 * 需要给加载变量的类上面加载@RefreshScope，在客户端执行POST请求 /refresh的时候就会更新此类下面的变量值。
 * 
 * @date 2017年11月9日
 */
@RestController
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
public class ConfigClientController {
	
	@Value("${sample.hello}")
    private String helloProp;

    @RequestMapping("/sampleHello")
    public String from() {
        return this.helloProp;
    }
}
