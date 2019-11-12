package com.husky.service;/**
 * Created by husky on 2019/11/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangkai
 * @create 2019-11-05 8:37
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    //@CacheResult(cacheKeyMethod = "getCacheKey")
    //@HystrixCommand(fallbackMethod = "callback")
    public String hi(int id){
        JSONObject json = new JSONObject();
        json.put("name","husky");
        System.out.println("1111");
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://SERVER3000/hi", json, String.class);
        return stringResponseEntity.getBody();
    }


    private String callback(int id){
        return "error";
    }


    private String getCacheKey(int id){
        return String.valueOf(id);
    }

}
