package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CountdownLaunch 测试
 * @author zhangkai
 * @create 2018-11-13 20:41
 **/
public class CdlThreadDemo implements Runnable {

    private CountDownLatch countDownLatch;

    private long num;

    public CdlThreadDemo(CountDownLatch countDownLatch, long num) {
        this.countDownLatch = countDownLatch;
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("休眠时间---"+num);
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("执行完毕");
    }
}
