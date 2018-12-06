package com.zk.socket;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName SocketDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/6 11:54
 **/
public class SocketDemo {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("172.18.1.187", 4242);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        StringBuilder sb = new StringBuilder();
        sb.append("SUB").append(" ").append("a.b").append(" ").append("dev").append("\r\n");
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();
        new SocketDemo.ReadIn(inputStream).start();
        System.out.println(getOne());

        System.in.read();
    }

    public static int getOne(){
        while (true){
            return 1;
        }
    }


    public static class ReadIn extends Thread {
        Socket socket;
        InputStream inputStream;

        public ReadIn(Socket socket) {
            this.socket = socket;
        }

        public ReadIn(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            //InputStream inputStream = socket.getInputStream();
            boolean flag = false;
            while (true) {
                try {
                    int tmp;
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    String tag ;
                    while((tmp = this.inputStream.read()) != -1){
                        char c = (char)tmp;
                        sb.append(c);
                        if (c != ' ' && c != '\r' && c != '\n'){

                        }else {
                            System.out.println(sb.toString());
                            int a ;
                            while(true){
                                a = this.inputStream.read();
                                if (a != '\n') {
                                    sb2.append((char)a);
                                    continue;
                                }
                                System.out.println(sb2.toString());
                                break;
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
