package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import java.io.IOException;

/**
 * 通过实现接口来实现线程
 *
 * @author zhangkai
 * @create 2018-11-13 19:14
 **/
public class ThreadRunDemo implements Runnable {

    boolean flag = true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("线程进来:" + Thread.currentThread());
                //Thread.sleep(5000);   //sleep 5秒
                if (this.flag) {
                    wait();
                } else {
                    notify();  //notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread());
        }
    }


    public static void main(String[] args) throws Exception {
        ThreadRunDemo demo = new ThreadRunDemo();
        new Thread(demo).start();
        new Thread(demo).start();
        new Thread(demo).start();
        new Thread(demo).start();
        Thread.sleep(5000);
        demo.setFlag(false);
        new Thread(demo).start();
        System.in.read();
    }


}
