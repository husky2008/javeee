package com.zk.jms;/**
 * Created by husky on 2018/11/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 生产者服务
 *
 * @author zhangkai
 * @create 2018-11-17 14:35
 **/

@Service("producerService")
public class ProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    /**
     * 发送消息
     * @param destination
     * @param msg
     */
    public void sendMsg(Destination destination,String msg){
        jmsMessagingTemplate.convertAndSend(destination,msg);
    }

    //@JmsListener(destination = "spring.boot.mq2")
    public  void receive(String msg){
        System.out.println("我是生产者收到了你的回应"+msg);
    }








}
