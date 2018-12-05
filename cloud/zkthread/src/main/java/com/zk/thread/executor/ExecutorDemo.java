package com.zk.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的使用
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/5 15:11
 **/
public class ExecutorDemo {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //执行线程
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        });

    }



}
