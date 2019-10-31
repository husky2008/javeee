package com.zk.socket.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket Server 端
 *
 * @ClassName SocketServerDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/19 10:42
 **/
public class SocketServerDemo {


    public static void main(String[] args) throws Exception {


        ServerSocket serverSocket = new ServerSocket(8888);
        //serverSocket.setSoTimeout(1000*60*60);
        Socket socket = serverSocket.accept();  //阻塞监听等待服务器端的请求过来
        InputStream inputStream = socket.getInputStream();

        System.out.println(parseInput(inputStream)+"------------");

        inputStream.close();
        socket.close();
    }


    public static String parseInput(InputStream inputStream) throws IOException {

        int flag;
        StringBuilder sb = new StringBuilder();
        while ((flag = inputStream.read()) != -1) {
            char a = (char) flag;
            sb.append(a);
        }
        return sb.toString();
    }


}
