package com.zk;/**
 * Created by husky on 2018/11/16.
 */

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @author zhangkai
 * @create 2018-11-16 11:04
 **/
public class Demo {


    public void say(){
        System.out.println("demo");
    }

    public static int tryDemo() {
        int a = 1;
        try {
            a = a + 1;
            throw  new RuntimeException("111");
        } catch (Exception e) {

        } finally {
            a = 3;
        }
        return  a;
    }


    public static void main(String[] args) {

//            System.out.println(Demo.tryDemo());

        DemoSon demoSon = new DemoSon();
        demoSon.say();
        Demo demo = (Demo)demoSon;
        demo.say();
    }
}


class DemoSon extends  Demo{
    @Override
    public void say() {
      System.out.println("demo son");

        TreeMap<String,Integer> a = new TreeMap<>();
        a.put("3",3);
        a.put("1",1);
        a.put("2",2);


        Collection<Integer> values = a.values();
        Collections.sort((List<Integer>)values);



        System.out.println(values);
    }
}





