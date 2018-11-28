package zk.provider.domain;/**
 * Created by husky on 2018/11/1.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author zhangkai
 * @create 2018-11-01 16:38
 **/
public class UserInfo implements Serializable {
    private Long id;
    private String name;
    private int age;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
