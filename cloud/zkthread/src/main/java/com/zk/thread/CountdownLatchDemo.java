package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import java.util.concurrent.CountDownLatch;

/**
 *  一个线程等待其他线程完成各自的工作后再执行
 *  例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。
 * @author zhangkai
 * @create 2018-11-13 20:45
 **/
public class CountdownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(new CdlThreadDemo(countDownLatch,15000)).start();
        new Thread(new CdlThreadDemo(countDownLatch,16000)).start();
        new Thread(new CdlThreadDemo(countDownLatch,11000)).start();
        new Thread(new CdlThreadDemo(countDownLatch,17000)).start();
        new Thread(new CdlThreadDemo(countDownLatch,9000)).start();
        countDownLatch.await();
        System.out.println("-------执行完毕-------");
    }

}
