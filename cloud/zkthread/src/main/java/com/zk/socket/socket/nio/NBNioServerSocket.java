package com.zk.socket.socket.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Reactor 模式,响应者模式
 * @ClassName NBNioServerSocket
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/10/23 11:43
 **/
public class NBNioServerSocket {

    private static Selector selector;
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static Charset charset = Charset.forName("utf8");

    static {
        try {
            selector = Selector.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
        //SocketChannel accept = serverSocketChannel.accept();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if(next.isAcceptable()){
                    SocketChannel accept = serverSocketChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_READ);
                    System.out.println("---read----");
                }else if(next.isReadable()){
                    ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
                    SocketChannel channel = (SocketChannel)next.channel();
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    String receiveData = charset.decode(byteBuffer).toString();
                    System.out.println(receiveData);
                }

            }
        }


    }
}
