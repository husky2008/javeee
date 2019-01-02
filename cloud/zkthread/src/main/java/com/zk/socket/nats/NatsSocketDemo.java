package com.zk.socket.nats;

import java.io.*;
import java.net.Socket;

/**
 * 通过socket 连接nat测试,java端订阅消息,并实时监控消息订阅通道
 *
 *
 * nats 服务端发送消息 pub a.b 5 (a.b 主题, 5:表示要发送消息的长度,这个必须和要发送的一样,不然发布失败)
 *
 * @ClassName SocketDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/6 11:54
 **/
public class NatsSocketDemo {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("172.18.5.245", 4242);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        StringBuilder sb = new StringBuilder();
        new NatsSocketDemo.ReadIn(inputStream,outputStream).start();
        new NatsSocketDemo.PingThread(outputStream).start();
        sb.append("SUB").append(" ").append("a.b").append(" ").append("dev").append("\r\n");
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();

        System.in.read();
    }

    public static int getOne(){
        while (true){
            return 1;
        }
    }


    public static class PingThread extends Thread{
        OutputStream outputStream;

        public PingThread(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(10000);
                    this.outputStream.write("PING\r\n".getBytes());
                    this.outputStream.flush();
                    System.out.println("ping");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ReadIn extends Thread {
        Socket socket;
        InputStream inputStream;
        OutputStream outputStream;

        Long interTime = 10000L;

        public ReadIn(Socket socket) {
            this.socket = socket;
        }

        public ReadIn(InputStream inputStream,OutputStream outputStream) {
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        public  StringBuilder parsetMsg(InputStream inputStream)throws Exception{
            int temp;
            StringBuilder topic = new StringBuilder();
            StringBuilder content = new StringBuilder();
            while((temp = inputStream.read()) != -1){
                char c = (char)temp;
                if(c != '\r'){
                    topic.append(c);
                    continue;
                }else{
                   //解析msg信息
                    int a;
                    while((a = inputStream.read()) != -1){
                        char aChar = (char)a;
                        if(aChar != '\r'){
                            content.append(aChar);
                            continue;
                        }
                        break;
                    }
                }
                return content;
            }
            return content;
        }

        @Override
        public void run() {
            //InputStream inputStream = socket.getInputStream();
            boolean flag = false;
            while (true) {
                try {
                    Long currentTime = System.currentTimeMillis();
                    Long lastTime = System.currentTimeMillis();
                    if(currentTime - lastTime > interTime){
                        /*this.outputStream.write("PING\r\n".getBytes());
                        this.outputStream.flush();*/
                    }
                    int tmp;
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    String tag ;

                    /**
                     * 因为是socket this.inputStream.read()会一直阻塞,不会出现 =-1的情况
                      */

                    String CMD = "";
                    while((tmp = this.inputStream.read()) != -1){
                        char c = (char)tmp;
                        /**
                         * nats订阅返回的数据分为3行,第一行为INFO,MSG,ERR等消息,接下来才是真正返回的数据
                         * 1.根据换行解析第一行的数据
                         */

                        if (c != ' ' && c != '\r' && c != '\n'){
                            sb.append(c);
                        }else {
                            //如果来的是消息
                            if("MSG".equals(sb.toString())){
                                StringBuilder stringBuilder = parsetMsg(this.inputStream);
                                System.out.println(stringBuilder.toString());
                            }else if("INFO".equals(sb.toString())){
                                //如果是INFO
                                int a ;
                                while((a = this.inputStream.read()) != -1){
                                    char tmp2 = (char)a;
                                    if (tmp2 != '\n') {
                                        sb2.append(tmp2);
                                        continue;
                                    }
                                    break;
                                }
                                System.out.println(sb2.toString());
                            }else if("+OK".equals(sb.toString())){

                            }
                            //将数据清空
                            sb.delete(0,sb.length()-1);
                            sb2.delete(0,sb2.length()-1);
                        }

                    }
                } catch (Exception e) {
                    System.out.println("------");
                    e.printStackTrace();
                }
            }
        }
    }

}
