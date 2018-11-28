package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障测试
 * 可重用的屏障
 * CyclicBarrier 达到屏障点执行成功后，需要执行的屏障操作
 *  互相等待，共同达到屏障点。
 * @author zhangkai
 * @create 2018-11-13 20:45
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("执行完毕");
            }
        });
        new Thread(new CycThreadDemo(cyclicBarrier,5000)).start();
        new Thread(new CycThreadDemo(cyclicBarrier,6000)).start();
        new Thread(new CycThreadDemo(cyclicBarrier,1000)).start();
        new Thread(new CycThreadDemo(cyclicBarrier,117000)).start();
        new Thread(new CycThreadDemo(cyclicBarrier,19000)).start();

        System.out.println("---------执行完毕");
    }

}
