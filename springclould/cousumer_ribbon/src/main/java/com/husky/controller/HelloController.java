package com.husky.controller;/**
 * Created by husky on 2019/11/4.
 */

import com.husky.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangkai
 * @create 2019-11-04 13:41
 **/
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;
    @RequestMapping("hello")
    public String hi(){
        return helloService.hi(1);
    }


}
