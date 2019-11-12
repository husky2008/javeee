package com.husky.entity;/**
 * Created by husky on 2019/11/5.
 */

/**
 * @author zhangkai
 * @create 2019-11-05 11:56
 **/
public class User {

    private  int id;
    private String name;


    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
