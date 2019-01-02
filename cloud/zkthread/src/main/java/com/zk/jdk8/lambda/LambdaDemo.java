package com.zk.jdk8.lambda;

/**
 * @ClassName LambdaDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/5 17:48
 **/
public class LambdaDemo {


    public static void main(String[] args) {
        M m = new M();
        m.say(new L() {
            @Override
            public void say() {
                System.out.println("funck");
            }
        });
        //lambda写法:函数式接口编程写法
        m.say(() -> System.out.println("abc"));

        //XData::isTrue 静态方法调用形式
        M m2 = new M("abc",XData::isTrue);
    }


}


class M {

    String name;
    I i;

    public M() {
    }

    public M(String name, I i) {
        this.name = name;
        this.i = i;
    }

    interface I {
        Boolean isMatch();
    }

    public void say(L l) {
        //l.say();
        System.out.println("abc");
    }

    public String say1() {
        return "def";
    }

    public String abc(String abc) {
        return "fuck";
    }


}

class XData {

    public XData() {
    }

    public static   boolean isTrue (){
        System.out.println("XData:istrue");
        return true;
    }

}


interface L {
    void say();
}