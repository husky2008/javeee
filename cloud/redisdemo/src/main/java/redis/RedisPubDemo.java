package redis;/**
 * Created by husky on 2018/11/17.
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * redis消息发布端
 *
 * @author zhangkai
 * @create 2018-11-17 20:43
 **/
public class RedisPubDemo {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("47.105.52.174",6379);
        Jedis resource = jedisPool.getResource();
        String channel = "redis_mq";
        for (int i=0;i<10;i++){
            resource.publish(channel,"redis实现的消息发布订阅"+i);  //发布消息
        }
        System.out.println("消息发送成功");
    }
}
