package com.zk.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * @ClassName singleThreadPool
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/21 17:19
 **/
public class singleThreadPool {


    public static void main(String[] args) {


        /**
         * 和fixedThreadPool没有什么区别,只是指定了poolSize=maxPoolSize=1
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            try{
                Thread.sleep(2000);
            }catch(Exception e){
                e.printStackTrace();
            }
            executorService.submit(()->{
                System.out.println(Thread.currentThread());
            });
        }

    }





}
