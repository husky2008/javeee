package zk.provider.zkdemo;/**
 * Created by husky on 2018/11/18.
 */

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * 工作节点
 *
 * @author zhangkai
 * @create 2018-11-18 17:23
 **/
public class WorkerServer {


    private String serverPath;  //server服务信息
    private String configPath; //配置路径
    private ServiceConfig serviceConfig;
    private  ServerData serverData;


    private ZkClient zkClient;
    private IZkDataListener zkDataListener;

    public WorkerServer(String serverPath, String configPath, ServiceConfig serviceConfig, ServerData serverData, ZkClient zkClient) {
        this.serverPath = serverPath;
        this.configPath = configPath;
        this.serviceConfig = serviceConfig;
        this.serverData = serverData;
        this.zkClient = zkClient;
        this.zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object data) throws Exception {
                System.out.println("new Work server config is:" + data);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        };
    }


    public void init(){
        String path = serverPath.concat("/").concat(serverData.getAddress());
        //创建临时节点并保存数据
        if(!zkClient.exists(serverPath)){
            zkClient.createEphemeral(serverPath,"manager".getBytes());
        }

        //zkClient.createEphemeral(path,serverData.toString().getBytes());

        //订阅配置节点,当发生改变是同步到自己节点的配置信息
        //zkClient.subscribeDataChanges(configPath,zkDataListener);
    }


    public  void stop(){
        System.out.println("worker server stop ");
        //取消配置节点的订阅
        zkClient.unsubscribeDataChanges(configPath,zkDataListener);
    }




}
