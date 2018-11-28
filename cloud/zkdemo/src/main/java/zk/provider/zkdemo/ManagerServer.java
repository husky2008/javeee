package zk.provider.zkdemo;/**
 * Created by husky on 2018/11/18.
 */

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 工作节点
 *
 * @author zhangkai
 * @create 2018-11-18 17:23
 **/
public class ManagerServer {


    private String serverPath;  //server服务信息
    private String configPath; //配置路径
    private ServiceConfig serviceConfig;
    private  ServerData serverData;
    private String commandPath;
    private List<String> serverList;


    private ZkClient zkClient;
    private IZkDataListener zkDataListener;  //监听节点数据的变化
    private IZkChildListener zkChildListener;  //监听子节点的变化

    public ManagerServer(String serverPath, String configPath, ServiceConfig serviceConfig, String commandPath, ZkClient zkClient) {
        this.serverPath = serverPath;
        this.configPath = configPath;
        this.serviceConfig = serviceConfig;
        this.commandPath = commandPath;
        this.zkClient = zkClient;
        this.zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object data) throws Exception {
                System.out.println("监听命令节点数据的变化："+data);
                String str = new String ((byte[]) data);
                if("list".equals(str)){
                    System.out.println(serverList.toString());
                }
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        };

        this.zkChildListener = new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                //动态打印子节点数据的变化
                serverList = list;
                System.out.println("子节点的数据:"+list.toString());
            }
        };
    }


    public void init(){
        zkClient.subscribeDataChanges(commandPath,zkDataListener);  //订阅令节点变化
        zkClient.subscribeChildChanges(serverPath,zkChildListener); //订阅子节点变化
    }


    public  void stop(){
        System.out.println("worker server stop ");
        //取消配置节点的订阅
        zkClient.unsubscribeDataChanges(commandPath,zkDataListener);  //订阅令节点变化
        zkClient.unsubscribeChildChanges(serverPath,zkChildListener); //订阅子节点变化
    }




}
