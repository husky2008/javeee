package com.husky.kafka.singleDemo;/**
 * Created by husky on 2019/10/30.
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * kafka生产者
 * @author zhangkai
 * @create 2019-10-30 12:01
 **/
public class Producer {

    public static final String brokerList = "47.93.118.77:9092";
    public static final String topic = "husky";


    public static void main(String[] args) throws  Exception{
        Properties properties = new Properties();


        /**
         * ProducerConfig 包含了生产者配置的所有参数,简化属性的编写
         * properties.put("key.serializer", StringSerializer.class.getName());
         * properties.put("value.serializer",StringSerializer.class.getName());
         * 可以用下面的代替
         */

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());


        properties.put("bootstrap.servers",brokerList);
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);
        //ProducerRecord 返回的消息体
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic,"hello,kafka");
        Future<RecordMetadata> send = kafkaProducer.send(producerRecord);



        /**
         * 分区拦截器
         *  Partitioner
         *  DefaultPartitioner
         *  可以配置自定义的分区拦截器,将消息发送到特定的分区
         *  properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,Class.class.getName());
         */



        //同步发送,等待发布结果返回
        RecordMetadata recordMetadata = send.get();
        System.out.println(recordMetadata);




        //异步发送
        kafkaProducer.send(producerRecord,(recordMetadata1, e) -> {
            if(e != null){
                e.printStackTrace();
            }else{
                System.out.println(recordMetadata1);
            }

        });

        kafkaProducer.close();

    }


}
