package com.zk;/**
 * Created by husky on 2018/11/16.
 */

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author zhangkai
 * @create 2018-11-16 11:04
 **/
public class Demo {


    private long a;

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

       /* DemoSon demoSon = new DemoSon();
        demoSon.say();
        Demo demo = (Demo)demoSon;
        demo.say();*/

        /*System.out.println(2|1);
        System.out.println(129|128);*/
        List<String> nodeName = new ArrayList<>();
        String name = "";
        String join = StringUtils.join(nodeName, ",");
        System.out.println(Arrays.asList(name.split(",")));
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





