package com.zk.jdk8.stream;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Predicate:函数接口,用于判断传入的参数是否符合条件,返回boolean值 boolean accept(T t);
 * @ClassName StudentStreamDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/25 9:58
 **/
public class StudentStreamDemo {

    public static void main(String[] args) {

        List<Student> students = Collections.emptyList();
        for (int i=0;i<10;i++){
           Student student = new Student();
           student.setId(i);
           student.setAge(i+20);
           student.setName("husky"+i);
           students.add(student);
        }


        //过滤student.age > 5的数据,然后返回对应的的ID集合列表
        List<Integer> collect = students.parallelStream().filter(student -> student.getAge() > 5).map(student -> student.getId()).collect(Collectors.toList());

        System.out.println(collect);

    }

}
