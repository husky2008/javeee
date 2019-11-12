package com.husky.service.impl;/**
 * Created by husky on 2019/11/5.
 */

import com.husky.entity.User;
import com.husky.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangkai
 * @create 2019-11-05 13:14
 **/
@Component
public class UserServiceFallback implements UserService{
    @Override
    public String hello1(@RequestBody User user) {
        return "hello1 error";
    }

    @Override
    public String hello2(@RequestParam("name") String name) {
        return "hello2 error";
    }

    @Override
    public User hello3(@RequestHeader("id") int id, @RequestHeader("name") String name) {
        User user = new User();
        user.setName("error");
        return user;
    }
}
