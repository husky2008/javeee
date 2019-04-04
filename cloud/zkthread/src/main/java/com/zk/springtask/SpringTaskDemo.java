package com.zk.springtask;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName SpringTaskDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/26 14:42
 **/

@Component
@EnableScheduling
public class SpringTaskDemo implements SchedulingConfigurer {


    public static String cron = "0/2 * * * * ?";
    private static  int a = 0;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
            System.out.println("abc"+ new Date());
            a++;
            if(a == 10){
                cron = "0/10 * * * * ?";
            }
        }, triggerContext -> {
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExec = trigger.nextExecutionTime(triggerContext);
            return nextExec;
        });
    }


}
