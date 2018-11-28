package zk.rpc;/**
 * Created by husky on 2018/11/19.
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.reflect.generics.tree.VoidDescriptor;

/**
 * 服务中心
 *
 * @author zhangkai
 * @create 2018-11-19 22:21
 **/
public interface RpcServer {

    void  stop(); //停止服务
    void start(); //开始服务
    void register(Class serviceI, Class serviceImpl); //注册服务
    Integer getPort();  //获取端口
    Boolean isRuning();  //是否在运行
}
