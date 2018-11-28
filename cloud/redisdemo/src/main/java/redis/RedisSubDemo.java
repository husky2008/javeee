package redis;/**
 * Created by husky on 2018/11/18.
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * redis消息订阅端
 *
 * @author zhangkai
 * @create 2018-11-18 15:45
 **/
public class RedisSubDemo {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("47.105.52.174",6379);
        Jedis resource = jedisPool.getResource();
        //通过redis实现简单的发布订阅模式
        String channel = "redis_mq";
        resource.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(message);
            }
        },channel);
    }
}
