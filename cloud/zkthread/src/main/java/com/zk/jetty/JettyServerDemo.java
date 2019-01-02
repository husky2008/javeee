package com.zk.jetty;

import com.zk.jetty.servlet.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * 内嵌Jetty Server 形式启动代码
 * @ClassName JettyServerDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/21 14:39
 **/
public class JettyServerDemo {


    public static void main(String[] args) throws Exception {

        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(8888);
        server.addConnector(serverConnector);


        //上下文的Handler
        ServletContextHandler servletContextHandler = new ServletContextHandler();

        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(new ServletHolder(new HelloServlet()),"/hello");

        server.setHandler(servletContextHandler);
        server.start();
        server.join();
    }



}
