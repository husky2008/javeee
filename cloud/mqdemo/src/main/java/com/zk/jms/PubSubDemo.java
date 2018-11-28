package com.zk.jms;/**
 * Created by husky on 2018/11/16.
 */

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * 发布、订阅模式
 *  发布过消息之后,如果没有接受者,后来的接受者就永远不会受到这个消息了。
 *  多个订阅者订阅一个发布者的消息,会接受一次发布者的消息
 * @author zhangkai
 * @create 2018-11-16 19:44
 **/
public class PubSubDemo {

    public static final String brokerURL = "tcp://47.105.52.174:61616";

    public static void sentMsg(String msg)throws  Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /**
         * 目的地为topic
         */
        Topic myTopic = session.createTopic("myTopic");
        MessageProducer messageProducer = session.createProducer(myTopic);
        TextMessage textMessage = session.createTextMessage(msg);
        messageProducer.send(textMessage);
        messageProducer.close();
        session.close();
        connection.close();
    }


    /**
     * 消息接受者
     * @param str
     * @throws Exception
     */
    public static void receiveMsg(String str) throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("myTopic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(str + "我接受到了topic消息，消息内容是:" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();

    }


    public static void main(String[] args) throws Exception {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PubSubDemo.receiveMsg("线程1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PubSubDemo.receiveMsg("线程2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PubSubDemo.receiveMsg("线程3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //PubSubDemo.sentMsg("我是通过代码传递过来的消息!");


    }

}
