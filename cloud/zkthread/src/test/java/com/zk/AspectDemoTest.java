package com.zk;/**
 * Created by husky on 2018/11/13.
 */

import com.zk.aspect.AspectService;
import com.zk.aspect.HelloServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 线程的创建和初始化
 *
 * @author zhangkai
 * @create 2018-11-13 19:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectDemoTest {


    @Autowired
    private AspectService demoService;

    @Autowired
    private HelloServiceImpl helloService;

    @Test
    public void test(){
        /*demoService.say("what");
        */
        //demoService.hello("hello");
        demoService.sayw(helloService);
    }









}
