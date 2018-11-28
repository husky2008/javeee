package com.zk;/**
 * Created by husky on 2018/11/13.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * java 队列test
 *
 * @author zhangkai
 * @create 2018-11-13 16:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueTest {


    /**
     * 生产者线程对其的插入操作put必须等待消费者的移除操作take，反过来也一样。
     不像ArrayBlockingQueue或LinkedListBlockingQueue，SynchronousQueue内部并没有数据缓存空间，
     你不能调用peek()方法来看队列中是否有数据元素，因为数据元素只有当你试着取走的时候才可能存在，不取走而只想偷窥一下是不行的，
     当然遍历这个队列的操作也是不允许的。队列头元素是第一个排队要插入数据的线程，而不是要交换的数据。数据是在配对的生产者和消费者线程之间直接传递的，
     并不会将数据缓冲数据到队列中。可以这样来理解：生产者和消费者互相等待对方，握手，然后一起离开。
     * @throws Exception
     */

    @Test
    public void synchronousQue() throws Exception {
        SynchronousQueue<String> sq = new SynchronousQueue<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        sq.put("ti="+i);
                        System.out.println("向队列中添加："+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        Thread.sleep(3000);
                        String ab = sq.take();
                        System.out.println("从队列中取出："+ab);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
    }





    @Test
    public void LinkedBlockingQue() throws Exception {
        LinkedBlockingDeque<String> sq = new LinkedBlockingDeque<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        sq.put("ti="+i);
                        System.out.println("向队列中添加："+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try {
                        Thread.sleep(3000);
                        String ab = sq.take();
                        System.out.println("从队列中取出："+ab);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
    }









}
