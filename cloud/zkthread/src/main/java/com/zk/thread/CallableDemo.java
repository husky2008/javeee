package com.zk.thread;/**
 * Created by husky on 2018/11/13.
 */

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 返回计算结果的多线程
 *
 * @author zhangkai
 * @create 2018-11-13 20:15
 **/
public class CallableDemo implements Callable<Integer> {


    public   int ab = 1;

    @Override
    public  Integer call() throws Exception {
        Vector<String> vector = new Vector<>();
        return 1;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 类的继承和实现结构
         * C:FutureTask -> I:RunnableFuture -> I:Future,I:Runnable
         */


        FutureTask<Integer> integerFutureTask = new FutureTask<>(new CallableDemo());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(integerFutureTask);
        System.out.println(integerFutureTask.get());
    }


}
