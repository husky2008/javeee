package com.zk.thread.current;/**
 * Created by husky on 2018/11/14.
 */

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写分离锁
 * ReentrantReadWriteLock --> ReadWriteLock
 * 如果一个线程已经占用了读锁，如果其他线程需要申请写锁，则申请写锁的线程会一直等下读锁的释放；申请读锁不会阻塞
 * 如果一个线程已经占用了写锁，这其他线程如果申请读锁或写锁，都会阻塞，只到写锁释放
 * @author zhangkai
 * @create 2018-11-14 13:09
 **/
public class ReadWriteLockDemo {


    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void get(Thread thread) {
        readWriteLock.readLock().lock();
        try {
            Thread.sleep(5000);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + "获取到读锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void write(Thread thread) {
        readWriteLock.writeLock().lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread() + "获取到写锁");
            }
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockDemo.write(Thread.currentThread());
            }
        }).start();
        new Thread(new Runnable() {
               @Override
               public void run() {
                   readWriteLockDemo.get(Thread.currentThread());
               }
           }).start();
    }


}
