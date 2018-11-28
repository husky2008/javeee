package zk.provider.zkdemo;/**
 * Created by husky on 2018/11/14.
 */

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhangkai
 * @create 2018-11-14 23:48
 **/
public class ZookeeperDemo {

    private ZooKeeper zooKeeper;
    private static  String url = "127.0.0.1";
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public  void connect(){
        try {
            zooKeeper = new ZooKeeper(url, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                     if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                         System.out.println("链接成功");
                         countDownLatch.countDown();
                     }
                }
            });
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void createNode(String name) throws  Exception{
        String path = "/" + name;
        String s = zooKeeper.create(path, "husky".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("节点信息："+s);
    }

    public void close(){
        if(zooKeeper != null){
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    public static void main(String[] args) throws Exception {
        ZookeeperDemo zookeeperDemo = new ZookeeperDemo();
        zookeeperDemo.connect();  //获取链接成功
        zookeeperDemo.createNode("zk");
        zookeeperDemo.close();
        System.out.println("abc");
    }
}
