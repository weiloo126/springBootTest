package com.example.springBootTest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 外部web应用服务器构建Web Application Context的时候，会把启动类添加进去。（打war包时用到，修改启动类）
 *   
 * @date 2017年9月12日
 */
public class ServletInitializer extends SpringBootServletInitializer {
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	// 注意这里要指向原先用main方法执行的Application启动类
        return application.sources(Application.class);
    }
    
}
