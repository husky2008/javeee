package com.zk.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName NettyConfig
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/2/19 15:34
 **/
public class NettyConfig {
    /**
     * 储存每一个客户端接入进来时的channel对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /**
     * 服务器端口号
     */
    public static final int port = 8888;
    /**
     * WebSocket地址
     */
    public static final String WEB_SOCKET_URL="ws://localhost:"+port+"/websocket";
}
