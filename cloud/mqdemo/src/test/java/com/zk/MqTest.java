package com.zk;/**
 * Created by husky on 2018/11/17.
 */

import com.zk.jms.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author zhangkai
 * @create 2018-11-17 14:41
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {


    @Autowired
    private ProducerService producerService;

    @Test
    public void test(){
        /*for (int i=0;i<10;i++){*/
            ActiveMQQueue destination = new ActiveMQQueue("spring.boot.mq");
       //ActiveMQTopic destination = new ActiveMQTopic("spring.boot.mq.topic");
        producerService.sendMsg(destination,"我的第一个springboot集成activeMQ例子----");
        /*}*/
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
