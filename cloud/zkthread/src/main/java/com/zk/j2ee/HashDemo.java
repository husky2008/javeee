package com.zk.j2ee;/**
 * Created by husky on 2018/11/15.
 */

import java.util.HashMap;

/**
 * @author zhangkai
 * @create 2018-11-15 17:35
 **/
public class HashDemo {

    public static void main(String[] args) {


        /**
         * String equals 方法对比的就是字符串中字符是否相等
         * String hashCode 返回的是重写后的东西
         * Object 类的equals方法比较的就是 ==
         * Object hashCode 返回的是对象的内存地址
         */

        HashDemo h = new HashDemo();
        HashDemo h2 = new HashDemo();
        String a = "123";
        System.out.println(a.hashCode());
        System.out.println(h.hashCode());
        System.out.println(h2.hashCode());

        /**
         * Collection/Map
         */

        HashMap<String,Object> map = new HashMap<>(4);

    }
}
