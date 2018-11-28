package com.zk;/**
 * Created by husky on 2018/11/13.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangkai
 * @create 2018-11-13 14:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class VolatileTest {

    private   int a  = 1;

    @Test
    public  void testV(){
       Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
              a = 6;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread());
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("----------------");
                try {
                    //t.join();   //等待thread线程执行完,才会继续执行下面的代码
                    System.out.println("----------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(a);
            }
        });
        thread1.start();
        t.start();
    }

}
