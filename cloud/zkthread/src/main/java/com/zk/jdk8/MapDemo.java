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
        map.put("b","1");


        /**
         * 如果map中不存在对应key的value,将第二个参数的返回值返回作为key的value,
         * MapDemo::get   -->   MapDemo.get(a)  a是第一个参数的值
         * 如果map中存在对应的key,则不覆盖直接返回对应的值
         * computeIfAbsent 返回插入的值
         */

        String val =  map.computeIfAbsent("a",MapDemo::get);

        /**
         * 如果map中不存在对应的key,value,则放入map中,并返回null
         * 如果map中存在对应的key,value;不会进行覆盖,直接返回对应的value
         */
        String va2 = map.putIfAbsent("a","c");

        {
            map.put("aaa","1111");
        }

        System.out.println(val);
        System.out.println(va2);
        System.out.println(map);
    }

    public  static  String get(String str){

        return str + "def";
    }



}
