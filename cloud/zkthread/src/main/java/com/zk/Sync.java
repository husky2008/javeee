package com.zk;

/**
 * @ClassName Sync
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/5/21 17:02
 **/
public class Sync {


    public static synchronized void abc() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaa");
    }


    public static void main(String[] args) {

        String format = String.format("%s-%s", "a", "%s");
        System.out.println(format);
        System.out.println(String.format(format,"b"));


    }


}
