package com.zk.j2ee.enumdemo;

/**
 * 枚举类型的基本操作
 *
 * @ClassName BaseDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/22 15:56
 **/
public class BaseDemo {


    enum A {
      ADD,MODIFY,a
    }


    public static void main(String[] args) {
        A a = Enum.valueOf(A.class, "a");
        System.out.println(a);
    }


}
