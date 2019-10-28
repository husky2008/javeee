package com.zk.socket.socket.nio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NioSocketClient
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/10/23 10:42
 **/
public class NioSocketClient {

    public static void main(String[] args) throws  Exception{

        SocketChannel channel = SocketChannel.open();
        InetSocketAddress localhost = new InetSocketAddress("localhost", 8888);
        channel.connect(localhost);
        channel.configureBlocking(true);
        OutputStream outputStream = channel.socket().getOutputStream();
        String msg = "fuck";
        outputStream.write(msg.getBytes());
        outputStream.flush();
        outputStream.close();
        channel.close();


    }
}
