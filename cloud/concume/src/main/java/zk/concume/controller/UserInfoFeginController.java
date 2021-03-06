package zk.concume.controller;/**
 * Created by husky on 2018/11/1.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zk.concume.service.FeginService;

/**
 * @author zhangkai
 * @create 2018-11-01 17:11
 **/

@RestController
@RequestMapping("user")
public class UserInfoFeginController {


    @Autowired
    private FeginService feginService;

    @GetMapping("id/{id}")
    public String getUserById(@PathVariable("id") long id){

        return  feginService.getInfo(1l);
    }







}
