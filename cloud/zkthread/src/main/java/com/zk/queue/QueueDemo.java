package com.zk.queue;

import sun.applet.Main;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName QueueDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/6 9:27
 **/
public class QueueDemo {


    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(1);
        strings.take();
        System.out.println("处理完毕");
    }


}
