package redis;/**
 * Created by husky on 2018/11/17.
 */

import com.sun.org.apache.regexp.internal.RE;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 集群访问
 *
 * @author zhangkai
 * @create 2018-11-17 22:02
 **/
public class RedisClusterDemo {

    public static void main(String[] args) {


        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        for(int i=1;i<=6;i++){
            System.out.println(6660+i);
            hostAndPortSet.add(new HostAndPort("47.105.52.174",6660+i));
        }
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet);




    }


}
