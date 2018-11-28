package zk.rpc;/**
 * Created by husky on 2018/11/19.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangkai
 * @create 2018-11-19 22:26
 **/
public class RpcServerCenter implements RpcServer {

    //根据可用处理器个数初始化线程池
    public static final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    //注册的服务
    public static  Map<String, Class> registerService = new HashMap();

    public static Boolean isRuning = false;  //是否正在运行

    private Integer port;  //端口号


    @Override
    public void stop() {
        isRuning = false;
        service.shutdown();
    }

    @Override
    public void start() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("服务端启动.....");
            while (true) {
                //接受请求,如果有请求过来,交给一个线程处理
                System.out.println("请求已经过来");
                service.execute(new ServiceTask(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static class ServiceTask implements Runnable {


        private Socket client;

        public ServiceTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            //处理从客户端传递过来的请求
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;


            try {
                ois = new ObjectInputStream(client.getInputStream());
                String serviceName = ois.readUTF();
                System.out.println("读出服务名称");
                String methodName = ois.readUTF();
                System.out.println("读出方法名称");
                Class<?>[] parmType = ( Class<?>[]) ois.readObject(); //参数类型
                Object[] param = (Object[]) ois.readObject();  //参数值

                //实例化对象
                Class impl = registerService.get(serviceName);
                Method method = impl.getMethod(methodName, parmType);
                Object result = method.invoke(impl.newInstance(), param);
                oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    oos.close();
                    ois.close();
                    client.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
    }


    @Override
    public void register(Class serviceI, Class serviceImpl) {
        System.out.println(serviceI.getName());
        registerService.put(serviceI.getName(), serviceImpl);
    }

    @Override
    public Integer getPort() {
        return port;
    }

    @Override
    public Boolean isRuning() {
        return isRuning;
    }

    public RpcServerCenter(int port) {
        this.port = port;
    }

    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                RpcServer rpcServer = new RpcServerCenter(8888);
                rpcServer.register(HelloService.class,HelloServiceImpl.class);
                rpcServer.start();
            }
        }).start();


        HelloService helloService =  (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class<?>[]{HelloService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //生成代理类的同时去掉用,远程服务,将对应的参数信息传递过去
                Socket client = new Socket();
                client.connect(new InetSocketAddress("localhost", 8888));

                //构建输出流
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                outputStream.writeUTF(HelloService.class.getName());
                System.out.println("写入服务名称。");
                outputStream.writeUTF(method.getName());
                System.out.println("写入方法名称。");
                outputStream.writeObject(method.getParameterTypes());  //参数类型
                outputStream.writeObject(args);  //参数


                //同步阻塞等待返回
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                return objectInputStream.readObject();
            }
        });

       System.out.println(helloService.sayHi("abc"));;


    }


}
