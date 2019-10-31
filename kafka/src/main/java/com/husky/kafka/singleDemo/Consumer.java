package com.husky.kafka.singleDemo;/**
 * Created by husky on 2019/10/30.
 */

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Kafka消费者
 * @author zhangkai
 * @create 2019-10-30 12:01
 **/
public class Consumer {

    public static final String brokerList = "47.93.118.77:9092";
    public static final String topic = "husky";
    public static final String groupId = "group.demo";


    public static void main(String[] args) {
        Properties properties = new Properties();



        //properties.put("key.deserializer", StringDeserializer.class.getName());
        //properties.put("value.deserializer",StringDeserializer.class.getName());
        //简化写法
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());




        properties.put("bootstrap.servers",brokerList);
        properties.put("group.id",groupId);
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
        while (true){
            ConsumerRecords<Object, Object> poll = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<Object, Object> record : poll) {
                System.out.println(record.value());
            }
        }


    }


}
