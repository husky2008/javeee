package com.zk.design.proxy;/**
 * Created by husky on 2018/11/15.
 */

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JAVA中静态代理/动态代理的总结
 * 动态代理：
 *    JDK:InvocationHandler,Proxy
 * Cglib: MethodInterceptor
 * @author zhangkai
 * @create 2018-11-15 17:56
 **/
public class ProxyDemo {
}


interface I {
    void sayHello();
}

class A implements I {
    @Override
    public void sayHello() {
        System.out.println("我是A");
    }


    public void sayHi(){
        System.out.println("跟着我我一起sayHi");
    }
}


/**
 * 静态代理的实现
 * 代理类需要和目标类实现同一个接口,在代理类中维护目标类的引用
 * 缺点：随着代理的增多，需要维护的对象太多.2如果接口增加了方法,代理和实现都要做修改
 *
 */
class ProxyA implements I {

    private A a;

    public ProxyA(A a) {
        this.a = a;
    }


    @Override
    public void sayHello() {
        System.out.println("我是a的代理,我在你之前做了一点事");
        a.sayHello();
        System.out.println("我是a的代理,我在你之后做了一点事");
    }
}

/***
 * 通过JDK实现动态代理,InvocationHandler,Proxy
 * 1.优点,代理类只有一个就可以了,不用强引用被代理的类
 * 2.缺点,被代理的类必须实现接口
 */

class JDKProxyA implements InvocationHandler{

    private Object target;

    public JDKProxyA(Object target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理,方法前执行");
        method.invoke(target,args);
        System.out.println("jdk动态代理,方法后执行");
        return null;
    }

}


/**
 * 通过CGLIB的方式实现动态代理
 * 1.相对于JDK,不局限于实现了接口的类
 * 2.通过生成子类的方式实现动态代理,所以代理类不能是final类型的
 * 3.如果方法为static,private,final不会处理
 */
class CGLIBProxy implements MethodInterceptor{

    private Object target;

    public CGLIBProxy(Object target){
        this.target = target;
    }

    /**
     * 给目标对象创建子类
     * @return
     */
    public Object getTarget(){
        Enhancer en = new Enhancer();  //工具类
        en.setSuperclass(target.getClass());  //设置父类
        en.setCallback(this); //设置回调函数
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("CGLIB动态代理-------before");
        method.invoke(target,objects);
        System.out.println("CGLIB动态代理-------after");
        return null;
    }
}


class CGLIBProxy2{

    private Object target;

    public CGLIBProxy2(Object target){
        this.target = target;
    }

    /**
     * 给目标对象创建子类
     * @return
     */
    public Object getTarget(){
        Enhancer en = new Enhancer();  //工具类
        en.setSuperclass(target.getClass());  //设置父类


        /**
         * MethodInterceptor 继承了callback
         * 这里也相当于了类上实现了MethodInterceptor接口
         */
        en.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("CGLIB动态代理-------before");
                method.invoke(target,objects);
                System.out.println("CGLIB动态代理-------after");
                return null;
            }
        }); //设置回调函数
        return en.create();
    }
}



class Test {

    public static void main(String[] args) {

        /*A a = new A();
        ProxyA proxyA = new ProxyA(a);
        proxyA.sayHello();*/

       /* A a = new A();
        JDKProxyA jdkProxyA = new JDKProxyA(a);
        I o = (I)Proxy.newProxyInstance(A.class.getClassLoader(), A.class.getInterfaces(), jdkProxyA);
        o.sayHello();*/

        A a = new A();
        a = (A)new CGLIBProxy2(a).getTarget();
        a.sayHello();
        //a.sayHi();
    }

}
