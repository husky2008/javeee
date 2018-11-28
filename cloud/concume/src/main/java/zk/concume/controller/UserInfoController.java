package zk.concume.controller;/**
 * Created by husky on 2018/11/1.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zk.concume.vo.UserInfo;

/**
 * @author zhangkai
 * @create 2018-11-01 17:11
 **/

@RestController
@RequestMapping("wap/user")
public class UserInfoController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("id/{id}")
    public String getUserById(@PathVariable("id") long id){
        String url = "http://Provider/user/info/"+id;
        String forObject = restTemplate.getForEntity(url, String.class).getBody();
        return  forObject;
    }







}
