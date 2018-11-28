package com.zk.jms;/**
 * Created by husky on 2018/11/17.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSSessionMode;

/**
 * 消息接受者
 *
 * @author zhangkai
 * @create 2018-11-17 14:38
 **/

@Component
public class ConsumerService {


    /*@Bean
    public JmsListenerContainerFactory<?> topicFactory(ConnectionFactory factory) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        defaultJmsListenerContainerFactory.setConnectionFactory(factory);
        return  defaultJmsListenerContainerFactory;
    }*/

    @JmsListener(destination = "spring.boot.mq.topic",containerFactory ="topicFactory")  //注册监听,MessageListener
    //@SendTo("spring.boot.mq2")    //将返回的消息发送到队列spring.boot.mq2
    public void receive(String text){
      System.out.println("receive"+text);
      //return  "receive我收到了你到消息,现在我返回给你的消息---"+text;
    }


    @JmsListener(destination = "spring.boot.mq.topic",containerFactory ="topicFactory")  //注册监听,MessageListener
    //@SendTo("spring.boot.mq2")    //将返回的消息发送到队列spring.boot.mq2
    public void receive2(String text){
        System.out.println("receive2"+text);
        //return  "receive2我收到了你到消息,现在我返回给你的消息---"+text;
    }




}
