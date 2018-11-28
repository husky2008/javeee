package com.zk.jms;/**
 * Created by husky on 2018/11/15.
 */

/**
 * 单例模式
 * 平凡的创建和销毁对象,保证系统中只有该类一个实例对象。
 * @author zhangkai
 * @create 2018-11-15 19:15
 **/
public class SingletoneDemo {

    //恶汉模式,静态常量
    private static final SingletoneDemo sd = new SingletoneDemo();

    public static SingletoneDemo getSingletone() {
        return sd;
    }
}


/**
 * 恶汉模式,静态代码块
 */
class demo2 {
    private static final demo2 sd;

    static {
        sd = new demo2();
    }

    public static demo2 getSingletone() {
        return sd;
    }
}


/**
 * 懒汉模式,非线程安全
 */
class demo3 {
    private static demo3 sd;
    public static demo3 getSingletone() {
        if(sd == null){
            sd = new demo3();
        }
        return sd;
    }
}


/**
 * 线程安全,懒汉模式,同步方法
 */
class  demo4 {
    private static  demo4 sd;
    public static synchronized demo4 getSingletone() {
        if(sd == null){
            sd = new demo4();
        }
        return sd;
    }
}

/**
 * 懒汉模式,同步代码块,线程不安全
 */
class  demo5 {
    private static  demo5 sd;
    public static  demo5 getSingletone() {
        if(sd == null){
            //如果有多个线程进来,会引起线程问题
            synchronized(demo5.class){
                sd = new demo5();
            }
        }
        return sd;
    }
}

/**
 * 双重锁检查,线程安全,推荐用
 */
class  demo6 {
    private static volatile  demo6 sd;
    public static  demo6 getSingletone() {
        if(sd == null){
            //如果有多个线程进来,会引起线程问题
            synchronized(demo6.class){
                if(sd == null){
                    sd = new demo6();
                }
            }
        }
        return sd;
    }
}

/**
 * 静态内部类,推荐使用
 */
class demo7{
    private static class D {
        private  static  final demo7 sd = new demo7();
    }
    public static  demo7 getSd(){
        return D.sd;
    }
}


/**
 * 通过枚举的形式,不仅能解决线程安全问题,还能防止序列化重新创建对象
 * 相当于创建了一个对象,里面有2个对象 C,B
 */
enum A {
    B,C;
    public void whateverMethod() {
      System.out.println("hello");
    }
}



class test {
    public static void main(String[] args) {
        A.C.whateverMethod();
    }
}