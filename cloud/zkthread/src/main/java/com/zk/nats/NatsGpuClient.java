package com.zk.nats;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.nats.client.Connection;
import io.nats.client.Nats;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName NatsClient
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/7/31 10:25
 **/
public class NatsGpuClient {
    private static String COMPLETE_STATE = "Completed,Failed,Cancelled"; //有结束时间的状态

    private static final String msg = "{" +
            "\"cluster\": \"#CLUSTER\"," +
            "\"subcid\": \"#CLUSTER\"," +
            "\"hostid\": \"a90f41fe5e4a5b5b50c57669ca075ef1\"," +
            "\"hostname\": \"hebei8836\"," +
            "\"ip\": \"192.168.60.1\"," +
            "\"format\": \"1.0.0 L\"," +
            "\"timestamp\":#TIMESTAMP," +
            "\"#QUEUE_NAME\": {" +
            "\"state\": \"OK\"," +
            "\"timezone\": \"28800|CST\"," +
            "\"jobs\": {" +
            "\"last\": \"20180712141955\"," +
            //"\"format\": \"id|name|user|queue|stateo|ncpus|ngpus|nnodes|lnodes|submit_node|submit|start|end|elapse|err|rssmax(MB)|eapps|eapps\"," +
            "\"format\": \"id|name|user|queue|state|stateo|ncpus|ngpus|nnodes|lnodes|submit_node|submit|start|end|elapse|err|rssmax(MB)|software|eapps|platform|comment|output_size(byte)|enodes|priority\"," +
            "\"0\": [\"#ID\", \"#JOB_NAME\", \"#USER_NAME\", \"batch\", \"Running\",\"Running\",\"#CPU_NUM\",\"#GPU_NUM\",\"#NODES_NUM\", \"#NODES_NAME\", \"test01\", \"#SUBMIT_TIME\", \"#START_TIME\", \"#END_TIME\", \"#ELAPSE\",\"STAR1\",10000,\"STAR1\",\"STAR1\",\"STAR1\",\"STAR1\",\"1024\",\"#NODES_JOB\",1002]," +
            "\"cur\": 1," +
            "\"step\": 1," +
            "\"num\": 1" +
            "}" +
            "}" +
            "}";


    private static final String node_msg = "{" +
            "\"cluster\": \"#CLUSTER\"," +
            "\"subcid\": \"#CLUSTER\"," +
            "\"hostid\": \"a90f41fe5e4a5b5b50c57669ca075ef1\"," +
            "\"hostname\": \"hebei8836\"," +
            "\"ip\": \"192.168.60.127\"," +
            "\"format\": \"1.0.0 L\"," +
            "\"timestamp\":#TIMESTAMP," +
            "\"#QUEUE_NAME\": {" +
            "\"state\": \"OK\"," +
            "\"timezone\": \"28800|CST\"," +
            "\"nodes\": {" +
            "\"last\": \"20180712142020\"," +
            //"\"format\": \"nodename|cpuid|stateo|cpu_alloc|cpu_total|job_running|job_total|ljobs\"," +//项目
            "\"format\": \"nodename|state|stateo|cpu_alloc|cpu_total|job_running|job_total|ljobs|eapps|platform|ngpus\"," +
            "\"0\": [" +
            "\"#NODE_NAME\"," +
            "\"#NODE_STATE\"," +
            "\"free\"," +
            "\"#CPU_ALLOC\"," +
            "\"#CPU_TOTAL\"," +
            "1," +
            "1," +
            "1," +
            "\"eapps\"," +
            "\"platform\"," +
            "\"#NGPUS\"" +
            "]," +
            "\"cur\": 1," +
            "\"step\": 1," +
            "\"num\": 1" +
            //"}"+
            "}," +
            "\"queues\": {" +
            "\"last\": \"20180712142020\"," +
            //"\"format\": \"name|stateo|job_running|job_pending|nnodes|lnodes|ncpus_idle|ncpus|nusers|lusers\"," +
            "\"format\": \"name|state|stateo|job_running|job_pending|job_total|nnodes|lnodes|ncpus_idle|ncpus|nusers|lusers\"," +
            "\"0\": [" +
            "\"#QUEUES_NAME\"," +
            "\"#QUEUES_STATE\"," +
            "\"Execution\"," +
            "0," +
            "0," +
            "0," +
            "-1," +
            "-1," +
            "12, -1," +
            "\"*\"," +
            "\"*\"" +
            "]," +
            "\"cur\": 1," +
            "\"step\": 1," +
            "\"num\": 1" +
            "}" +
            "}" +
            "}";


    /**
     * 1.作业参数
     * type,ip,cluster,queueName,jobId,jobName,userName,period,subTime,startTime,endTime,elapse,nodesNameList,timeStamp,nnodes,ngpus
     * ip:nats ip 地址
     * cluster: 集群名称
     * jobId：作业id,
     * period: 统计间隔,
     * subTime: 提交时间,
     * startTime: 开始时间,
     * endTime:结束时间,没有传 -1,
     * elapse:运行时长,
     * nodesNameList:节点名称列表,格式node1,node2,node3,
     * timeStamp:采集时间
     * type: 0.作业信息,队列信息
     * nnodes:运行节点占用核数情况数组
     * ngpus:占用gpu卡数
     * 2.队列参数
     * type,ip,cluster,queueName,period,nodeName,jobIds,queuesName,nodeIds,timeStamp
     * ip:nats ip 地址
     * cluster: 集群名称
     * period: 统计间隔,
     * nodeName:节点名称
     * jobIds:该节点下的作业id列表，id1，id2,id3
     * queuesName:队列名称
     * nodeIds：该队列下的节点名称列表,node1,node2,node3
     * timeStamp:采集时间
     * type: 0.作业信息,队列信息
     */
    public static void main(String[] args) throws Exception {
//        String[] aa = {"1","172.18.4.206","paratera","PBS","sunTest","sunTest","sunyuanping","30000","1570845659000","1570845659000","-1","600000","test01","1570845659000",
//                "[[\"test01\"],[4]]","3"};
//        String[] aa = {"1","172.18.7.202","paratera","1571018459000","PBS","test01","Free","0","2","4","queue1","Free"};
//        args = aa;
        String type = args[0];
        switch (type) {
            case "0":
                if (true) {
                    String ip = args[1];
                    String cluster = args[2];
                    String queueName = args[3];
                    String jobId = args[4];
                    String jobName = args[5];
                    String userName = args[6];
                    String perid = args[7];  //毫秒
                    String subTime = args[8]; //提交时间,毫秒
                    String startTime = args[9]; //开始时间,毫秒
                    String endTime = args[10]; //结束时间,毫秒
                    String elapse = args[11]; //运行时长,毫秒
                    String nodesNameList = args[12];//节点名称列表
                    String timeStamp = args[13]; //采集时间
                    String nodeJob = args[14];//作业节点cpu核数关系
                    JSONArray array = JSONArray.parseArray(nodeJob);
                    String ngpus = args[15];//gpu卡数
                    String ncpus = args[16];//cpu核数
                    String host = String.format("nats://%s:4242", ip);
                    Connection nc = Nats.connect(host);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                String s = msg.replaceAll("#ID", jobId).replaceAll("#SUBMIT_TIME", subTime)
                                        .replaceAll("#START_TIME", startTime).replaceAll("#TIMESTAMP", timeStamp)
                                        .replaceAll("#NODES_NUM", nodesNameList.split(",").length + "")
                                        .replaceAll("#NODES_NAME", nodesNameList).replaceAll("#END_TIME", endTime)
                                        .replaceAll("#ELAPSE", elapse).replaceAll("#CLUSTER", cluster)
                                        .replaceAll("#JOB_NAME", jobName).replaceAll("#USER_NAME", userName)
                                        .replaceAll("#QUEUE_NAME", queueName).replaceAll("#GPU_NUM", ngpus)
                                        .replaceAll("#CPU_NUM", ncpus);
                                JSONObject object = JSONObject.parseObject(s);
                                JSONObject obsObject = object.getJSONObject(queueName);
                                JSONObject objectJob = obsObject.getJSONObject("jobs");
                                JSONArray array1 = objectJob.getJSONArray("0");
                                array1.set(22, array);
                                nc.publish("R." + cluster + ".jobs.jobs", JSONObject.toJSONString(object).getBytes("UTF-8"));
                                System.out.println("job succ. " + System.currentTimeMillis());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 0, Long.parseLong(perid));
                } else {
                    System.out.println("参数错误...");
                }
                break;
            case "1":
                if (true) {
                    String ip = args[1];
                    String cluster = args[2];
                    String timeStamp = args[3];
                    String queueName = args[4];
                    String nodeName = args[5];
                    String nodeState = args[6];
                    String cpuAlloc = args[7];
                    String cpuTotal = args[8];
                    String ngpus = args[9];
                    String queue = args[10];
                    String queueState = args[11];
                    String host = String.format("nats://%s:4242", ip);
                    Connection nc = Nats.connect(host);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                String s1 = node_msg.replaceAll("#CLUSTER", cluster).replaceAll("#NODE_NAME", nodeName)
                                        .replaceAll("#TIMESTAMP", timeStamp).replaceAll("#QUEUE_NAME", queueName)
                                        .replaceAll("#NODE_STATE", nodeState).replaceAll("#CPU_ALLOC", cpuAlloc)
                                        .replaceAll("#CPU_TOTAL", cpuTotal).replaceAll("#NGPUS", ngpus).replaceAll("#QUEUES_NAME", queue)
                                        .replaceAll("#QUEUES_STATE", queueState);
                                nc.publish("R." + cluster + ".jobs.system", s1.getBytes("UTF-8"));
                                System.out.println("queue succ. " + System.currentTimeMillis());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 0, Long.parseLong("30000"));
                }
                break;
            case "2":
                if (args.length == 15) {
                    String ip = args[1];
                    String cluster = args[2];
                    String queueName = args[3];
                    String jobIdPre = args[4];
                    String jobNamePre = args[5];
                    Long time = System.currentTimeMillis();

                    String userName = args[6];
                    String perid = args[7];  //毫秒
                    String subTime = args[8]; //提交时间,毫秒
                    String startTime = args[9]; //开始时间,毫秒
                    String endTime = args[10]; //结束时间,毫秒
                    String elapse = args[11]; //运行时长,毫秒
                    String nodesNameList = args[12];//节点名称列表
                    String timeStamp = args[13]; //采集时间
                    String host = String.format("nats://%s:4242", ip);
                    Connection nc = Nats.connect(host);
                    Integer jobNum = Integer.parseInt(args[14]); //采集时间
                    for (Integer i = 0; i < jobNum; i++) {
                        String jobId = String.format("%s_%s_%s", jobIdPre, i, time);
                        String jobName = String.format("%s_%s_%s", jobNamePre, i, time);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    String s = msg.replaceAll("#ID", jobId).replaceAll("#SUBMIT_TIME", subTime)
                                            .replaceAll("#START_TIME", startTime).replaceAll("#TIMESTAMP", timeStamp)
                                            .replaceAll("#NODES_NUM", nodesNameList.split(",").length + "").replaceAll("#NODES_NAME", nodesNameList)
                                            .replaceAll("#END_TIME", endTime).replaceAll("#ELAPSE", elapse).replaceAll("#CLUSTER", cluster)
                                            .replaceAll("#JOB_NAME", jobName).replaceAll("#USER_NAME", userName).replaceAll("#QUEUE_NAME", queueName);
                                    nc.publish("R." + cluster + ".jobs.jobs", s.getBytes("UTF-8"));
                                    System.out.println("job succ. " + System.currentTimeMillis());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 0, Long.parseLong(perid));
                    }
                } else {
                    System.out.println("参数错误...");
                }
                break;


        }


    }
}

