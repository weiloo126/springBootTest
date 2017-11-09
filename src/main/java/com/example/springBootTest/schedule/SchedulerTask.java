package com.example.springBootTest.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableScheduling //启用定时任务
@Component
public class SchedulerTask {
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    //@Scheduled(fixedRate = 5000, initialDelay = 1000) //第一次延迟1秒后执行，之后在上一次开始执行时间点之后5秒再执行
    public void reportCurrentTimeByFixedRate() {
        System.out.println("fixedRate 现在时间：" + dateFormat.format(new Date()));
    }   

    //@Scheduled(fixedDelay = 10000) //上一次执行完毕时间点之后5秒再执行
    public void reportCurrentTimeByFixedDelay() {
        System.out.println("fixedDelay 现在时间：" + dateFormat.format(new Date()));
    }

    //@Scheduled(cron="*/5 * * * * *")
    public void reportCurrentTimeByCronExpression() {
        System.out.println("cron 现在时间：" + dateFormat.format(new Date()));
    }
}