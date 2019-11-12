package com.husky.config;/**
 * Created by husky on 2019/11/6.
 */

import filter.AccessFilter;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangkai
 * @create 2019-11-06 3:55
 **/
@Configuration
public class ZuulConfig {


    @Bean
    AccessFilter accessFilter(){
        return  new AccessFilter();
    }


    /**
     * 自定义路由规则
     * @return
     */
    @Bean
    PatternServiceRouteMapper serviceRouteMapper(){
        return  new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
    }



}
