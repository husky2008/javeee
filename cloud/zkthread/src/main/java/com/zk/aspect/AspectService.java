package com.zk.aspect;

import org.springframework.stereotype.Service;

/**
 * @ClassName DemoService
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/11/29 18:23
 **/
@Service
public class AspectService implements IService {
    @AspectAnnotation
    @Override
    public  void say(String str){
        System.out.println(str);
    }


    public  void hello(String str){
        System.out.println(str);
    }
}
