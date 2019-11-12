package com.husky.config;/**
 * Created by husky on 2019/11/5.
 */

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhangkai
 * @create 2019-11-05 14:01
 **/
@Configuration
public class ConsumerConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }
}
