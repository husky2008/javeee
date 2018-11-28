package redis;/**
 * Created by husky on 2018/11/17.
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * redis单机版测试
 *
 * @author zhangkai
 * @create 2018-11-17 20:43
 **/
public class RedisDemo {

    public static void main(String[] args) {

        JedisPool jedisPool = new JedisPool("47.105.52.174",6379);
        Jedis resource = jedisPool.getResource();
        //通过redis实现简单的发布订阅模式
        String channel = "redis_mq";

        /*resource.lpush(channel.getBytes(),"ddddddddddddddd".getBytes());*/
        //System.out.println(resource.rpop(channel));

        //resource.lpush("list1","1","2","2","4","5");
        //resource.ltrim("list1",0,10);  //截取list的0到10个元素
        resource.lrem("list1",0L,"1");  //移除全部1


        System.out.println(resource.lrange("list1",0,1000));


        Set<String> set = resource.zrange("scores",0,10);
        System.out.println(set);


        resource.close();
        jedisPool.close();



    }
}
