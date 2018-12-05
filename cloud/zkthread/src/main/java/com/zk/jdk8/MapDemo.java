package com.zk.jdk8;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/5 18:07
 **/
public class MapDemo {


    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();


        /**
         * 如果map中不存在对应key的value,将第二个参数的返回值返回作为key的value,
         * MapDemo::get   -->   MapDemo.get(a)  a是第一个参数的值
         */

        map.computeIfAbsent("a",MapDemo::get);

        System.out.println(map.get("a"));


    }

    public  static  String get(String str){

        return str + "def";
    }



}
