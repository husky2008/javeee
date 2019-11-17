package com.zk.proxy.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName MyMethodInterceptor
 * @Description TODO
 * @Author husky
 * @Date 2019/11/17 15:22
 **/
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("befor");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return invoke;
    }
}
