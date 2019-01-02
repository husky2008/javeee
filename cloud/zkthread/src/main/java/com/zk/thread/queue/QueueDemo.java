package com.zk.thread.queue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName QueueDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/6 9:27
 **/
public class QueueDemo {


    public static void main(String[] args) throws InterruptedException {
        /*ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(1);
        strings.take();
        System.out.println("处理完毕");*/


        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        /**
         * 创建一个固定频率的任务
         * 3秒后开始执行,每隔2秒执行一次
         */
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        },3000,2000, TimeUnit.MILLISECONDS);

    }


}
