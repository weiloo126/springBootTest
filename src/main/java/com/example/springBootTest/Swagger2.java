package com.example.springBootTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 使用Swagger2构建强大的RESTful API文档
 * 启动Spring Boot程序，访问：http://localhost:8080/swagger-ui.html。就能看到前文所展示的RESTful API的页面。
 *   
 * @date 2017年10月17日
 */
@Configuration
@EnableSwagger2 //启用Swagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo()) //创建该Api的基本信息（这些基本信息会展现在文档页面中）
		.select() //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
		.apis(RequestHandlerSelectors.basePackage("com.example.springBootTest.controller")) //本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）
		.paths(PathSelectors.any())
		.build();
	}
	
	/**
	 * 创建该Api的基本信息（这些基本信息会展现在文档页面中）
	 * @return
	 */
	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
		.title("Spring Boot中使用Swagger2构建RESTful APIs")
		.description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
		.termsOfServiceUrl("http://blog.didispace.com/")
		.contact("程序猿DD")
		.version("1.0")
		.build();
	}
}
