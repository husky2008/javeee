package zk.provider.zkdemo;/**
 * Created by husky on 2018/11/18.
 */

/**
 * 配置信息类
 *
 * @author zhangkai
 * @create 2018-11-18 17:18
 **/
public class ServiceConfig {

    private String url;
    private String name;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "ServiceConfig{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
