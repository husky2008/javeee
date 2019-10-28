package com.zk.socket.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @ClassName SocketClientDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/19 10:48
 **/
public class SocketClientDemo {


    public static void main(String[] args) throws  Exception{


        Socket socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8888);
        socket.connect(inetSocketAddress);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        String msg = "hello";
        //Thread.sleep(10000);
        outputStream.write(msg.getBytes());


        inputStream.read();
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        socket.close();
    }




    public static  void sendMsg(OutputStream os,String msg)throws  Exception{
        os.write(msg.getBytes());
        os.flush();
    }
}
