package com.zk.thread.consumer;/**
 * Created by husky on 2018/11/15.
 */

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 消费者
 *
 * @author zhangkai
 * @create 2018-11-15 11:58
 **/
public class Consumer implements Runnable {
    private Queue<Integer> queue;
    private String name;
    private int max;


    public Consumer(String name, Queue<Integer> queue, int max) {
        this.queue = queue;
        this.name = name;
        this.max = max;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    System.out.println("队列中没有产品");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    int a = queue.poll();
                    System.out.println("取出产品:" + a);
                    queue.notifyAll();
                }
            }
        }
    }


    public static void main(String[] args) {




        Queue<Integer> queue = new LinkedList<>();
        new Thread(new Producter("1",queue,10)).start();
        new Thread(new Producter("2",queue,10)).start();
        new Thread(new Consumer("1",queue,10)).start();
        new Thread(new Consumer("1",queue,10)).start();
        new Thread(new Consumer("1",queue,10)).start();

    }
}

