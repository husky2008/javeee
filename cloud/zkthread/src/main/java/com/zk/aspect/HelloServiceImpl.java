package com.zk.aspect;

import org.springframework.stereotype.Component;

/**
 * @ClassName HelloServiceImpl
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/11/30 9:32
 **/

@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public void abc(){
        System.out.println("abc");
    }

    public HelloServiceImpl() {
    }
}
