package com.zk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/11/30 11:02
 **/
@RestController
public class DemoController {


    @RequestMapping("say")
    public String say(){
        return "hi";
    }




}
