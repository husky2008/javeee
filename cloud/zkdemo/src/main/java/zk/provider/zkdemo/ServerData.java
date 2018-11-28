package zk.provider.zkdemo;/**
 * Created by husky on 2018/11/18.
 */

/**
 * 服务器信息
 *
 * @author zhangkai
 * @create 2018-11-18 17:20
 **/
public class ServerData {

    private Integer id;
    private String address;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServerData{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
