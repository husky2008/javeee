package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import java.util.concurrent.CyclicBarrier;

/**
 * @author zhangkai
 * @create 2018-11-13 20:41
 **/
public class CycThreadDemo implements Runnable {

    private CyclicBarrier cyclicBarrier;

    private long num;

    public CycThreadDemo(CyclicBarrier cyclicBarrier, long num) {
        this.cyclicBarrier = cyclicBarrier;
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("休眠时间---" + num);
        try {
            //cyclicBarrier.await(2, TimeUnit.SECONDS);
            Thread.sleep(num);
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
