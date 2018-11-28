package com.zk.thread.current;/**
 * Created by husky on 2018/11/14.
 */

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁练习
 * ReentrantLock
 * ReentrantLock --> Lock
 * @author zhangkai
 * @create 2018-11-14 12:
 *
lock.lock();  获取锁,如果获取不到一直等待
lock.tryLock()  尝试获取锁,如果获取到返回true,没有获取到返回false
lock.lockInterruptibly() 获取锁,中途可以被打断
lock.tryLock(6, TimeUnit.SECONDS) 等待多长时间后,如果还是没有获取到锁返回false,后续执行别的代码
 *
 *
 *
 **/
public class ReentrantLockDemo {


    private ArrayList<Integer> a = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock();

    public void insert() throws InterruptedException {
        //lock.lock();
        //lock.tryLock()  尝试获取锁,如果获取到返回true
        //lock.lockInterruptibly();
            if(lock.tryLock(6, TimeUnit.SECONDS)){
                System.out.println("获取到锁："+Thread.currentThread());
                try{
                    Thread.sleep(7000);
                    for (int i = 0; i < 10; i++) {
                        a.add(i);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.out.println("释放了锁："+Thread.currentThread());
                }
            }else{
                System.out.println("没有获取到锁资源");
            }

    }


    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLockDemo.insert();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

       Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLockDemo.insert();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
       t2.start();
       //t2.interrupt();

    }


}
