package com.zk.jdk8;

/**
 * @ClassName LambdaDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/5 17:48
 **/
public class LambdaDemo {


    public static void main(String[] args) {


        M m = new M();
       /* m.say(new L() {
            @Override
            public void say() {
                System.out.println("funck");
            }
        });*/

        //lambda写法
        //m.say(() -> System.out.println("abc"));
        m.say((String a) -> "abc");

    }


}


class M {

    public void say(L l) {
        //l.say();
        System.out.println("abc");
    }
}


interface L {
     //say();
    String say(String str);
}