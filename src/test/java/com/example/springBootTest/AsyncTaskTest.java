package com.example.springBootTest;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springBootTest.async.AsyncTask;

/**
 *   
 * @date 2017年10月24日
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {

	@Autowired
	private AsyncTask task;
	
	@Test
	public void test() throws Exception {				
		long start = System.currentTimeMillis();
		
		Future<String> future1 = task.doTaskOne();
		Future<String> future2 = task.doTaskTwo();
		Future<String> future3 = task.doTaskThree();
		
		while(true){
			if(future1.isDone() && future2.isDone() && future3.isDone()){
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}
