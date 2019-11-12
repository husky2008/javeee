package com.husky.controller;/**
 * Created by husky on 2019/11/4.
 */

import com.husky.entity.User;
import com.husky.service.UserService;
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
    UserService userService;


    @RequestMapping("/hello")
    public String hello(){
        User user = new User();
        user.setId(1);
        user.setName("huskky");
        StringBuilder sb = new StringBuilder();
        String s = userService.hello1(user);
        sb.append(s).append("-");
        String snake = userService.hello2("snake");
        sb.append(snake).append("-");
        User user1 = userService.hello3(2, "fuck");
        sb.append(user1.toString());
        return sb.toString();

    }
}
