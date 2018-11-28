package zk.provider.controller;/**
 * Created by husky on 2018/11/1.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zk.provider.domain.UserInfo;
import zk.provider.service.UserInfoService;

/**
 * @author zhangkai
 * @create 2018-11-01 16:40
 **/
@RestController
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;



    @GetMapping("id/{id}")
    public UserInfo getUserById(@PathVariable("id") Long id){
        return  userInfoService.getUserById(id);
    }


    @GetMapping("info/{id}")
    public String getInfo(@PathVariable("id") Long id){
        System.out.println("provider1");
        return  "hello" + id;
    }




}
