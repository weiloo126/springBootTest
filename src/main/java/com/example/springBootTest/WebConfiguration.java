package com.example.springBootTest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RemoteIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *   
 * @date 2017年8月29日
 */
@Configuration
public class WebConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(WebConfiguration.class);
	
//	@Bean
//	public RemoteIpFilter remoteIpFilter(){
//		return new RemoteIpFilter();
//	}
//	
	@Bean
	public FilterRegistrationBean myFilter1Registration(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter1());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("myFilter1");
		registration.setOrder(1);
		return registration;
	}
//	
//	@Bean
//	public FilterRegistrationBean myFilter2Registration(){
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new MyFilter2());
//		registration.addUrlPatterns("/*");
//		registration.addInitParameter("paramName", "paramValue");
//		registration.setName("myFilter2");
//		registration.setOrder(2);
//		return registration;
//	}
//
	public class MyFilter1 implements Filter{

		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void doFilter(ServletRequest arg0, ServletResponse arg1,
				FilterChain arg2) throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) arg0;
			log.info("this is MyFilter1, requestUri:" + request.getRequestURI());
			arg2.doFilter(arg0, arg1);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
			
		}
		
	}
//
//	public class MyFilter2 implements Filter{
//
//		@Override
//		public void destroy() {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void doFilter(ServletRequest arg0, ServletResponse arg1,
//				FilterChain arg2) throws IOException, ServletException {
//			HttpServletRequest request = (HttpServletRequest) arg0;
//			log.info("this is MyFilter2, requestUri:" + request.getRequestURI());
//			arg2.doFilter(arg0, arg1);
//		}
//
//		@Override
//		public void init(FilterConfig arg0) throws ServletException {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
}
