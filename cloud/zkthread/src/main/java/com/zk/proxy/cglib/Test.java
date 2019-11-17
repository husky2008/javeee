package com.zk.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @ClassName Test
 * @Description TODO
 * @Author husky
 * @Date 2019/11/17 15:26
 **/
public class Test {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Hello o = (Hello)enhancer.create();
        o.say();
        o.say2();


    }


}
