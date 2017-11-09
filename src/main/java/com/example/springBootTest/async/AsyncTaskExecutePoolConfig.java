package com.example.springBootTest.async;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

///**
// * 自定义的@Async任务线程池
// * 
// *   
// * @date 2017年10月24日
// */
//@Configuration
//@EnableAsync //让@Async注解能够生效
//public class AsyncTaskExecutePoolConfig {
//
//    @Bean
//    public AsyncTaskExecutor taskExecutor() {  
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); 
//        executor.setThreadNamePrefix("Anno-Executor");
//        executor.setCorePoolSize(10); //Set the ThreadPoolExecutor's core pool size. Default is 1. 
//        executor.setMaxPoolSize(10); //Set the ThreadPoolExecutor's maximum pool size. Default is Integer.MAX_VALUE. 
//
//        // 设置当池满时再提交任务的拒绝策略
//        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//
//			@Override
//			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//				// TODO Auto-generated method stub
//				
//			}
//        });
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
//        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行 
//        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//
//        return executor;  
//    } 
//}

/**
 * 配置默认的@Async任务线程池
 * 如果我们想使用默认的线程池，但是只是想修改默认线程池的配置，那怎么做了，此时我们需要实现AsyncConfigurer类
 *   
 * @date 2017年10月25日
 */
@Configuration
@EnableAsync //让@Async注解能够生效
public class AsyncTaskExecutePoolConfig implements AsyncConfigurer{    
	
	private static final Logger log = LoggerFactory.getLogger(AsyncTaskExecutePoolConfig.class);
    
    @Override  
    public Executor getAsyncExecutor() {  
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();  
        executor.setCorePoolSize(10);    
        executor.setMaxPoolSize(10);      
        executor.setThreadNamePrefix("taskExecutor-");    
    
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务    
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行    
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
        executor.initialize();    
        return executor;    
    }  
  
    @Override  
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {// 异步任务中的异常处理  
        return new AsyncUncaughtExceptionHandler() {

			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) { 
                log.error("=========================="+ex.getMessage()+"=======================", ex);  
                log.error("exception method:"+method.getName());  
			}  
        };  
    }    
}  
