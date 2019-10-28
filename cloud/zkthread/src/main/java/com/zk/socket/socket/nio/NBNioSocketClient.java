package com.zk.socket.socket.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * i/o多路复用,事件机制(不要打电话给我们,我们会打电话给你)
 *
 * @ClassName NBNioSocketClient
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/10/23 11:12
 **/
public class NBNioSocketClient {

    private static Selector selector;
    private static AtomicInteger atomicInteger = new AtomicInteger();

    static {
        try {
            selector = Selector.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {

            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("localhost", 8888));
            channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isConnectable()) {
                    SocketChannel c = (SocketChannel)selectionKey.channel();
                    c.finishConnect();
                    InetSocketAddress remote = (InetSocketAddress) c.socket().getRemoteSocketAddress();
                    String host = remote.getHostName();
                    int port = remote.getPort();
                    System.out.println(String.format("访问地址: %s:%s 连接成功!", host, port));

                } else if (selectionKey.isReadable()) {
                    System.out.println("读就绪---");
                } else if (selectionKey.isWritable()) {
                    System.out.println("写就绪---");
                    SocketChannel c = (SocketChannel)selectionKey.channel();
                    Charset charset = Charset.forName("utf8");
                    c.write(charset.encode("数据长度" + atomicInteger.getAndIncrement()));
                    selectionKey.interestOps(SelectionKey.OP_READ); //修改感兴趣的事情
                    System.out.println("写入完毕---");
                }
            }
        }


    }


}
