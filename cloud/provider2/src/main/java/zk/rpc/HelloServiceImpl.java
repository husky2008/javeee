package zk.rpc;/**
 * Created by husky on 2018/11/19.
 */

/**
 * 服务实现
 *
 * @author zhangkai
 * @create 2018-11-19 22:20
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String str) {
        return "hello" + str;
    }
}
