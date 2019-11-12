package com.husky.service;/**
 * Created by husky on 2019/11/5.
 */

import com.husky.config.ConsumerConfig;
import com.husky.entity.User;
import com.husky.service.impl.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangkai
 * @create 2019-11-05 12:02
 **/
@FeignClient(value = "SERVER3000",fallback = UserServiceFallback.class,configuration = ConsumerConfig.class)
public interface UserService {

    @RequestMapping(value = "hello1", method = RequestMethod.POST)
    String hello1(@RequestBody User user);


    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    String hello2(@RequestParam("name") String name);


    @RequestMapping(value = "hello3", method = RequestMethod.GET)
    User hello3(@RequestHeader("id") int id,@RequestHeader("name") String name);


}
