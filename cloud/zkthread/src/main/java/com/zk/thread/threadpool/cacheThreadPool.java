package com.zk.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * @ClassName cacheThreadPool
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/21 16:41
 **/
public class cacheThreadPool {
    public static void main(String[] args) {


        /**
         *   新建的线程默认存活时间 60s
         * 1.工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程
         * 2.如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程
         * 3.在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。
         * 4.线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
         */
        ExecutorService executorService =
                Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try{
                Thread.sleep(5000);
            }catch(Exception e){
              e.printStackTrace();
            }
            executorService.submit(() -> {
                System.out.println("abc"+Thread.currentThread());
            });
        }
    }
}
