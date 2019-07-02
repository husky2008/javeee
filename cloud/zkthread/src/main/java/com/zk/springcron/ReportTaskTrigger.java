package com.zk.springcron;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * @ClassName ReportTaskTrigger
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/26 19:18
 **/
public class ReportTaskTrigger implements Trigger {
    private String cron;

    public ReportTaskTrigger(String cron) {
        this.cron = cron;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        CronTrigger trigger = new CronTrigger(cron);
        Date nextExecDate = trigger.nextExecutionTime(triggerContext);
        return nextExecDate;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
