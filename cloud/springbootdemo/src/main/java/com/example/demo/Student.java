package com.example.demo;/**
 * Created by husky on 2018/11/14.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 实体类
 *
 * @author zhangkai
 * @create 2018-11-14 14:38
 **/

@ConfigurationProperties(prefix = "student")
@Configuration
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
