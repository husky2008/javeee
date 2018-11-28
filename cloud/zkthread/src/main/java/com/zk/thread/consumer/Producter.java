package com.zk.thread.consumer;/**
 * Created by husky on 2018/11/15.
 */

import java.util.Queue;

/**
 * 生产者
 *
 * @author zhangkai
 * @create 2018-11-15 11:51
 **/
public class Producter implements Runnable {

    private Queue<Integer> queue ;
    private String name;
    private int max;
    private int i = 0;


    public Producter (String name,Queue<Integer> queue,int max){
        this.queue = queue;
        this.name = name;
        this.max = max;
    }



    @Override
    public void run() {
        while (true){
            synchronized (queue){
                if(queue.size() == max){
                    System.out.println("生成队列已经满了");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    queue.offer(i++);
                    System.out.println("放入产品："+i);
                    queue.notify();

                }
            }
        }

    }
}
