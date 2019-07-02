package com.zk.springcron;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 任务调度
 *
 * @ClassName TaskSchedule
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/26 18:38
 **/
@Component
public class TaskSchedule {


    private static ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    private static Map<MultiKey, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();

    static {
        threadPoolTaskScheduler.setPoolSize(128);
        threadPoolTaskScheduler.setThreadNamePrefix("Report-Task-Executor-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        threadPoolTaskScheduler.initialize();
    }


    public static void main(String[] args) {
        String cron = "0 */3 * * * ?";
        threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, new CronTrigger(cron));
    }


}
