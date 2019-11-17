package com.zk.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandler
 * @Description TODO
 * @Author husky
 * @Date 2019/11/17 14:49
 **/
public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MyInvocationHandler");
        return null;
    }
}
