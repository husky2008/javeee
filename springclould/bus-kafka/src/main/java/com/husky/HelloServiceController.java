package com.husky;/**
 * Created by husky on 2019/11/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.husky.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author zhangkai
 * @create 2019-11-05 3:22
 **/
@RestController
public class HelloServiceController {

    @Value("${server.port}")
    String port;

    @Value("${name}")
    String name;

    @RequestMapping("hello")
    public String sayHello(){
        return "hello" + ":" + port + ":" + name;
    }


    @RequestMapping(value = "hi",method = RequestMethod.POST)
    public String sayHi(@RequestBody JSONObject user){
        int nextInt = new Random().nextInt(3000);
        try {
            Thread.sleep(Long.valueOf(nextInt));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hi" + ":" + port + ":" + System.currentTimeMillis();
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(){
        int nextInt = new Random().nextInt(3000);
        try {
            Thread.sleep(Long.valueOf(nextInt));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "get" + ":" + port + ":" + System.currentTimeMillis();
    }




    @RequestMapping(value = "hello1",method = RequestMethod.POST)
    public String hello1(@RequestBody User user){
        //throw  new RuntimeException();
        System.out.println("hi" + ":" + port + ":" + user.toString());
        return "hi" + ":" + port + ":" + user.toString() + ",name=" + name + "hello1-----";
    }


    @RequestMapping(value = "hello2",method = RequestMethod.GET)
    public String hello2(@RequestParam("name") String name){
        return "hi" + ":" + port + ":" + name + "hello2-----" ;
    }


    @RequestMapping(value = "hello3",method = RequestMethod.GET)
    public User hello3(@RequestHeader int id,@RequestHeader String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }



}
