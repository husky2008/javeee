package com.zk.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @ClassName Test
 * @Description TODO
 * @Author husky
 * @Date 2019/11/17 14:50
 **/
public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        HelloService o = (HelloService)Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[]{HelloService.class}, new MyInvocationHandler());
        o.say();

    }
}
