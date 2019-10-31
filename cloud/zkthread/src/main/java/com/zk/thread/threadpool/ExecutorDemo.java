package com.zk.thread.threadpool;

import com.zk.jdk8.stream.Student;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池的使用
 *
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/5 15:11
 **/
public class ExecutorDemo {

    public static AtomicInteger a = new AtomicInteger(0);
    public static void main(String[] args) throws IOException {



      /*  ExecutorService executorService = Executors.newFixedThreadPool(2);

        //执行线程
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        });*/

        ThreadPoolExecutor threadPoolExecutor = null;
        /**
         * corePoolSize:核心线程数,一直在线程池中存活(allowCoreThreadTimeOut 可以设置不一直保存)
         * maximumPoolSize:最大线程数,超过会发放入队列中
         * keepAliveTime:线程存活时间
         * unit:存活时间单位
         * workQueue：线程池队列 SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue
         * threadFactory: 默认工厂 DefaultThreadFactory ,通过线程工厂可以对线程的一些属性进行定制。
         * RejectedExecutionHandler:当线程池中的资源已经全部使用，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法
         */
         threadPoolExecutor = new ThreadPoolExecutor(1, 3, 1,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(3), (runnable) -> {
            Thread t = new Thread(runnable);
            return t;
        }, (r, executor) -> {
            //超过最大线程数,才会执行这一步
            try {
                System.out.println(executor.getQueue().size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        /**
         * 队列问题点：
         * 1.如果线程数量>核心线程数，但<=最大线程数，并且任务队列是LinkedBlockingDeque的时候
         *    不指定队列大小:线程池的最大线程数设置是无效的，他的线程数最多不会超过核心线程数
         *    指定队列大小:超过最大线程数量的任务会放在任务队列中排队。
         * 2.如果线程数量>核心线程数，但<=最大线程数，并且任务队列是 ArrayBlockingQueue的时候
         *   超过最大线程数会放入到队列中
         *
         * 3.如果线程数量>最大线程数，并且任务队列是 SynchronousQueue的时候
         *   超过最大线程数会被拒绝,一般设置为 maximumPoolSize = Integer.MAX_VALUE
         */
        threadPoolExecutor = new ThreadPoolExecutor(1,1,1,TimeUnit.MINUTES,new ArrayBlockingQueue<>(5));

        threadPoolExecutor.setRejectedExecutionHandler((r, executor) -> {
            try {
                //重写保证每个线程任务都能够执行
                executor.getQueue().put(r);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        List<Student> s = new ArrayList<>();
        for(int i=0;i<10;i++){
            Student student = new Student();
            student.setId(i);
            s.add(student);
        }

        ConcurrentHashMap<String, List<Student>>  a = new ConcurrentHashMap<>();
        a.put("a",s);
        for(Student student : s){
            threadPoolExecutor.execute(new Task(student,a));
        }

        a.clear();
        s.clear();
        System.in.read();


    }
}


class Task implements Runnable{
    private  String abc;
    private  Student student;
    private  List<Student> students;
    ConcurrentHashMap<String, List<Student>>  a;
    public Task(Student student,List<Student> students) {
        this.student = student;
        this.students = students;
    }

    public Task(Student student,ConcurrentHashMap<String, List<Student>> a) {
        this.student = student;
        this.a = a;
    }


    @Override
    public void run() {
        try{
             if(student.getId()  == 0){
                 this.abc = "123";
                 Thread.sleep(5000);
             }
            //System.out.println(student.getId()  + "---" + students.size() +"" + a.get("a"));
            System.out.println(Thread.currentThread() +"----a:"+ abc+ "----" + student.getId()  + "---" + a.get("a"));
        }catch(Exception e){
          e.printStackTrace();
        }

    }
}
