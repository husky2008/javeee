package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import com.sun.javafx.font.t2k.T2KFactory;

import java.io.IOException;

/**
 * @author zhangkai
 * @create 2018-11-13 19:11
 **/
public class ThreadDemo extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        /*ThreadDemo demo = new ThreadDemo();
        Thread thread = new Thread(demo);
        Thread thread2 = new Thread(demo);
        thread.start();
        thread2.start();
        System.in.read();*/

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("abc");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread());
            }
        }).start();

        t1.start();
        System.out.println("main 执行完毕");

    }


}
