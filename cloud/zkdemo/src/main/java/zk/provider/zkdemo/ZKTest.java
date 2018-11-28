package zk.provider.zkdemo;/**
 * Created by husky on 2018/11/18.
 */

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 测试类
 *
 * @author zhangkai
 * @create 2018-11-18 17:51
 **/
public class ZKTest {


    public static String configPath = "/config";
    public static String commandPath = "/command";
    public static String serverPath = "/servers";
    private static  String url = "127.0.0.1:2181";
    public static void main(String[] args) throws IOException {

       //数据配置
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setName("mysql");
        serviceConfig.setUrl("jdbc:mysql://test");
        serviceConfig.setPassword("12345");

       ZkClient zkManager  = new ZkClient(url,5000,5000,new BytesPushThroughSerializer());

        ManagerServer managerServer = new ManagerServer(serverPath, configPath, serviceConfig,commandPath, zkManager);
        managerServer.init();




        //workServer
        ZkClient zkClient  = new ZkClient(url,5000,5000,new BytesPushThroughSerializer());
        ServerData serverData = new ServerData();
        serverData.setAddress("127.0.0.1");
        serverData.setId(1);
        serverData.setName("服务器1");
        WorkerServer workerServer = new WorkerServer(serverPath,configPath,serviceConfig,serverData,zkClient);
        workerServer.init();
        new BufferedReader(new InputStreamReader(System.in)).readLine();
















    }


}
