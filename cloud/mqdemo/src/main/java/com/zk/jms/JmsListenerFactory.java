package com.zk.jms;/**
 * Created by husky on 2018/11/17.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * 提供发布订阅模式的factory
 *
 * @author zhangkai
 * @create 2018-11-17 16:09
 **/
@Configuration
public class JmsListenerFactory {


    /**
     * topic模式
     * @param factory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> topicFactory(ConnectionFactory factory) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        defaultJmsListenerContainerFactory.setConnectionFactory(factory);
        return  defaultJmsListenerContainerFactory;
    }

    /**
     * p2p点对点模式
     * @param factory
     * @return
     */
   /* @Bean
    public JmsListenerContainerFactory<?> ptpFactory(ConnectionFactory factory) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(factory);
        return  defaultJmsListenerContainerFactory;
    }*/


}
