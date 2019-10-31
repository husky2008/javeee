package com.zk.thread.threadlocal;

/**
 * @ClassName ThreadLocalDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/10/24 9:21
 **/
public class ThreadLocalDemo {

    public  static  ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) throws Exception{
      new Thread(() -> {
          threadLocal.set("abc");
      }).start();
      new Thread(() -> {
          String s = threadLocal.get();
          System.out.println(s);
      }).start();

      System.in.read();
    }

}
