package com.zk.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * @ClassName fixedthreadPool
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/21 16:59
 **/
public class fixedthreadPool {


    public static void main(String[] args) {


        /**
         * 核心线程数和最大线程数一样大,创建之后会一直保留nThreads线程,超出放入队列中
         * ThreadPoolExecutor(nThreads, nThreads,
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>())
         */
        ExecutorService executorService = Executors.newFixedThreadPool(5);
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
