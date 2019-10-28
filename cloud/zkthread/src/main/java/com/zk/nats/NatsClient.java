package com.zk.nats;

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
public class NatsClient {
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
            "\"format\": \"id|name|user|queue|stateo|ncpus|ngpus|nnodes|lnodes|submit_node|submit|start|end|elapse|err|rssmax(MB)|eapps|eapps\"," +
            "\"0\": [\"#ID\", \"#JOB_NAME\", \"#USER_NAME\", \"batch\", \"Running\",12,#NODES_NUM, \"#NODES_NAME\", \"node01\", \"#SUBMIT_TIME\", \"#START_TIME\", \"#END_TIME\", \"#ELAPSE\",\"STAR1\"]," +
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
            "\"format\": \"nodename|cpuid|stateo|cpu_alloc|cpu_total|job_running|job_total|ljobs\"," +
            "\"0\": [" +
            "\"#NODE_NAME\"," +
            "\"56\"," +
            "\"free\"," +
            "4," +
            "8," +
            "#JOB_NUM," +
            "1," +
            "\"#JOBIDS\"" +
            "]," +
            "\"cur\": 1," +
            "\"step\": 1," +
            "\"num\": 10" +
            "}," +
            "\"queues\": {" +
            "\"last\": \"20180712142020\"," +
            "\"format\": \"name|stateo|job_running|job_pending|nnodes|lnodes|ncpus_idle|ncpus|nusers|lusers\"," +
            "\"0\": [" +
            "\"#QUEUES_NAME\"," +
            "\"Execution\"," +
            "0," +
            "0," +
            "#NNODES," +
            "\"#NODEID\"," +
            "12," +
            "12, -1," +
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
     * type,ip,cluster,queueName,jobId,jobName,userName,period,subTime,startTime,endTime,elapse,nodesNameList,timeStamp
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


        String type = args[0];
        switch (type) {
            case "0":
                if (args.length == 14) {
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
                    String host = String.format("nats://%s:4242", ip);
                    Connection nc = Nats.connect(host);
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
                                nc.publish("R.slurmcluster.jobs.jobs", s.getBytes("UTF-8"));
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
                if (args.length == 10) {
                    String ip = args[1];
                    String cluster = args[2];
                    String queueName = args[3];
                    String perid = args[4];
                    String nodeName = args[5];
                    String jobIds = args[6];
                    String queuesName = args[7];
                    String nodeIds = args[8];
                    String timeStamp = args[9];
                    String host = String.format("nats://%s:4242", ip);
                    Connection nc = Nats.connect(host);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                String s1 = node_msg.replaceAll("#CLUSTER", cluster).replaceAll("#NODE_NAME", nodeName)
                                        .replaceAll("#JOB_NUM", jobIds.split(",").length + "").replaceAll("#JOBIDS", jobIds)
                                        .replaceAll("#QUEUES_NAME", queuesName)
                                        .replaceAll("#NNODES", nodeIds.split(",").length + "").replaceAll("#NODEID", nodeIds)
                                        .replaceAll("#TIMESTAMP", timeStamp).replaceAll("#QUEUE_NAME", queueName);
                                nc.publish("R.slurmcluster.jobs.system", s1.getBytes("UTF-8"));
                                System.out.println("queue succ. " + System.currentTimeMillis());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 0, Long.parseLong(perid));
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
                                    nc.publish("R.slurmcluster.jobs.jobs", s.getBytes("UTF-8"));
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
