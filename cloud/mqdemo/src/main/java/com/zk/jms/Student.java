package com.zk.jms;/**
 * Created by husky on 2018/11/17.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * mq传输的对象
 *
 * @author zhangkai
 * @create 2018-11-17 13:07
 **/
public class Student implements Serializable{


    private static final long serialVersionUID = 1L;

    private  String name;
    private int age;
    private Date birthDate;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                '}';
    }
}
