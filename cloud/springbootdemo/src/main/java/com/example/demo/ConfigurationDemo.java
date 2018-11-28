package com.example.demo;/**
 * Created by husky on 2018/11/14.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 @ConditionalOnBean:当容器里有指定的bean的条件下。
 @ConditionalOnMissingBean:当容器里不存在指定bean的条件下。
 @ConditionalOnClass:当类路径下有指定类的条件下。
 @ConditionalOnMissingClass:当类路径下不存在指定类的条件下
 @ConditionalOnProperty:指定的属性是否有指定的值,比如@ConditionalOnProperties(prefix=”xxx.xxx”, value=”enable”, matchIfMissing=true),代表当xxx.xxx为enable时条件的布尔值为true,如果没有设置的情况下也为true

  * @author zhangkai
 * @create 2018-11-14 14:37
 **/

@Configuration
//@ConditionalOnClass(AODI.class)
@ConditionalOnMissingBean(type = {"com.example.demo.impl.BMW"})
@Import(ImportDemo.class)
public class ConfigurationDemo {

    /*@Bean
    public  Student getStudent(){
        return new Student();
    }*/

    @Value("student.name")
    public  String name;



    public ConfigurationDemo() {
        System.out.println("容器启动..........");
    }
}
