package com.zk.jms;/**
 * Created by husky on 2018/11/16.
 */

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消息中间件应用场景:
 * 解耦： 新增用户 + 发邮件/短信(这两步解耦)
 * 异步: 将不是很重要的业务,交给别的系统处理
 * 分布式系统之间的异步通信
 * 流量削锋
 *
 * 队列：缓解高并发下系统的压力。
 *
 *
 *
 * java消息系统
 * 点对点：会对消息进行保留,什么时候接收者来了,就会接受到消息
 * 如果发布了多条消息,接受者会一次性接受多条
 * @author zhangkai
 * @create 2018-11-16 18:09
 **/
public class JMSDemo {

    public static  final  String brokerURL = "failover:(tcp://47.105.52.174:61616,tcp://47.105.52.174:62626)?randomize=false";
    /**
     * 通过点对点的方式连接到ActiveMQ
     */
    public static void p2pSend() throws Exception {


        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        /**
         * 为了避免恶意代码引入了,安全机制,只允许指定包里的对象能够被传输
         * 可以通过setTrustAllPackages 设定接受所有包
         *setTrustedPackages:指定信任的包
         */
        //connectionFactory.setTrustAllPackages(true);

        //从工厂中获取链接
        Connection connection = connectionFactory.createConnection();

        //连接到ActiveMQ
        connection.start();

        /**
         * 发送消息,通过Session发送
         * 里面有2个参数
         * transacted：是否使用事务,如果为true表示使用,第二个参数就没什么用了
         * 如果为false,第二个参数有用,用于设置应答方式:自动应答,手动应答
         * Session.AUTO_ACKNOWLEDGE:只要接受到就应答接受成功,不管是否出现异常等。
         * Session.CLIENT_ACKNOWLEDGE:客户应答模式,通过客户端去告诉mq,我已经处理成功，如果客户端一直没有应答,会一直保留消息，重启mq后会重新发送。
         */
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        //创建消息发送的目的地Destination
        Queue myQ = session.createQueue("myQ");

        //创建消息的发送者
        MessageProducer producer = session.createProducer(myQ);


        /**
         *  设置生产者的模式,默认是持久化的
         *  DeliveryMode.PERSISTENT：持久化,当mq关闭的时候,队列数据将会保存
         *  DeliveryMode.NON_PERSISTENT : mq 关闭的时候队列数据不会保存
         */
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //消息的内容

        /***
         * TextMessage:普通文本
         * ObjectMessage:传递对象
         */
        /**
         *  传递文本
         */
        TextMessage message = session.createTextMessage("hello,我的第一次ActiveMQ之旅");
        /**
         *  传递对象,要设定connectionFactory.setTrustAllPackages(true)信任的包
         ObjectMessage message = session.createObjectMessage();
         Student s = new Student();
         s.setName("husky");
         s.setAge(11);
         s.setBirthDate(new Date());
         message.setObject(s);
         */


        /**
         * 字节流
         * BytesMessage message = session.createBytesMessage();
         */


        //传递map类型的message
        /*MapMessage message =  session.createMapMessage();
        message.setString("abc","123");
        message.setBoolean("flag",true);*/


        /**
         * 设置消息的过期时间
         * 过期的消息会从队列中清除，并存储到ActiveMQ.DLQ这个队列里面
         */
        //producer.setTimeToLive(5000*10);

        //开始发送消息
        producer.send(message);

        producer.close();
        session.close();
        connection.close();
    }


    /**
     * 创建点对点的消息接收方
     * @throws Exception
     */

    public static  void receive() throws Exception {


        //创建连接工厂,获取链接
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connectionFactory.setTrustAllPackages(true);

         //获取链接
         Connection connection = connectionFactory.createConnection();


         //连接到mq
         connection.start();


         //通过session接受消息
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        //创建接受的目的地,从什么地方接受消息
        Queue myQ = session.createQueue("myQ");

        //创建消费者,接受者
        MessageConsumer consumer = session.createConsumer(myQ);


         //消息什么时候过来不知道,需要监听

        /**
         * 通过监听的形式,接受端是被动接受,如果是高并发的情况下，要根据处理端的处理能力去接受消息
         * 可以改变接收端主动去接受
         */

        //5000中内是否有能力接受
       /* Message receive = consumer.receive(5000);

        if(receive != null){
            TextMessage  msg = (TextMessage)receive;
            System.out.println(Thread.currentThread() + ":接收方接受到消息:"+msg.getText());
            receive.acknowledge();
        }else{
            System.out.println("没有能力处理这条消息");
        }*/

        
        
        

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage  msg = (TextMessage)message;
                try {
                    System.out.println(Thread.currentThread() + ":接收方接受到消息:"+msg.getText());

                    // 手动确认收到消息
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

                /**
                 *  ObjectMessage objectMessage =  (ObjectMessage)message;
                 try {
                 System.out.println("------"+objectMessage.getObject());
                 } catch (JMSException e) {
                 e.printStackTrace();
                 }*/

               /* MapMessage objectMessage =  (MapMessage)message;
                try {
                    System.out.println("------"+objectMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }














    public static void main(String[] args) throws Exception {
       /* for(int i=0;i<5;i++){
            JMSDemo.p2pSend();
        }*/


        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    JMSDemo.receive();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    JMSDemo.receive();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    JMSDemo.receive();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/


       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JMSDemo.receive();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/






          //JMSDemo.p2pSend();


         JMSDemo.receive();
    }


}
