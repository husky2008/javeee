package com.zk.jdk8.stream;

import java.util.*;
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
        List<Student> students = new ArrayList<>();
        for (int i=0;i<10000000;i++){
           Student student = new Student();
           student.setId(i);
           student.setAge(i+20);
           student.setName("husky"+i);
           students.add(student);
        }
        /*for (int i = 0; i < 10000000; i++) {
            Student student = new Student();
            student.setId(i);
            student.setAge(i + 20);
            student.setName("husky" + i);
            students.add(student);
        }*/

        long time2 = System.currentTimeMillis();
        Set<String> keys = new HashSet<>();
        for(Student student:students){
          String key = String.format("%s%s", student.getId(), student.getName());
          keys.add(key);
        }
        System.out.println(System.currentTimeMillis() - time2);


      /*  long time = System.currentTimeMillis();
        Set<String> collect = students.stream().map(student -> String.format("%s%s", student.getId(), student.getName())).collect(Collectors.toSet());
        System.out.println(System.currentTimeMillis() - time);
        //collect.forEach(System.out::println);

        long time3 = System.currentTimeMillis();
        Set<String> collect2 = students.parallelStream().map(student -> String.format("%s%s", student.getId(), student.getName())).collect(Collectors.toSet());
        System.out.println(System.currentTimeMillis() - time3);*/

        //List<Student> collect1 = students.stream().filter(student -> student.getId() > 5).sorted(Comparator.comparing(Student::getId).reversed()).collect(Collectors.toList());
        //collect1.forEach(System.out::println);


        //过滤student.age > 5的数据,然后返回对应的的ID集合列表
        //List<Integer> collect = students.parallelStream().filter(student -> student.getAge() > 5).map(student -> student.getId()).collect(Collectors.toList());

       // System.out.println(collect);

    }

}
