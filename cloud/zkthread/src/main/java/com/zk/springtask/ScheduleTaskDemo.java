package com.zk.springtask;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName ScheduleTaskDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/26 15:04
 **/
public class ScheduleTaskDemo {

    public  static Map<String, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();
    private static int a = 0;


    public static void main(String[] args) throws Exception {

        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.initialize();
        ScheduledFuture<?> schedule = poolTaskScheduler.schedule(() -> {
            System.out.println("2秒执行一次" + new Date());
        }, new CronTrigger("* 55/5 * * * ?"));

        scheduledFutureMap.put("2",schedule);

        ScheduledFuture<?> schedule1 = poolTaskScheduler.schedule(() -> {
            System.out.println("10秒执行一次" + new Date());
        }, new CronTrigger("* 0/10 * * * ?"));

        scheduledFutureMap.put("10",schedule1);

        Thread.sleep(10000);
        boolean cancel = scheduledFutureMap.get("2").cancel(true);
        while (!cancel){
            System.out.println("取消任务");
            scheduledFutureMap.get("2").cancel(true);
        }


    }



















}
