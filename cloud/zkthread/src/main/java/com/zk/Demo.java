package com.zk;/**
 * Created by husky on 2018/11/16.
 */

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.TransactionDefinition;

import java.nio.channels.SelectionKey;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangkai
 * @create 2018-11-16 11:04
 **/
public class Demo {


    private long a;


    @Scheduled
    public void say() {
        System.out.println("demo");
    }

    public static int tryDemo() {
        int a = 1;
        try {
            a = a + 1;
            throw new RuntimeException("111");
        } catch (Exception e) {

        } finally {
            a = 3;
        }
        return a;
    }


    public static void main(String[] args) {

//            System.out.println(Demo.tryDemo());

       /* DemoSon demoSon = new DemoSon();
        demoSon.say();
        Demo demo = (Demo)demoSon;
        demo.say();*/

        /*System.out.println(2|1);
        System.out.println(129|128);*/
        /*List<String> nodeName = new ArrayList<>();
        String name = "";
        String join = StringUtils.join(nodeName, ",");
        System.out.println(Arrays.asList(name.split(",")));*/

       /* String currentVersion = "2.4.0";
        String licenseVersion = "2.6.0";
        if (StringUtils.isNotBlank(currentVersion) && StringUtils.isNotBlank(licenseVersion)) {
            String[] versionArr = currentVersion.split("\\.");
            String[] licenseVersionArr = licenseVersion.split("\\.");
            if (versionArr.length > 2) {
                versionArr = Arrays.copyOfRange(versionArr, 0, 2);
            }
            if (licenseVersionArr.length > 2) {
                licenseVersionArr = Arrays.copyOfRange(licenseVersionArr, 0, 2);
            }
            System.out.println(StringUtils.join(versionArr, "."));
            System.out.println(StringUtils.join(licenseVersionArr, "."));
        } else {
            // ret.put("versionValid", false);
        }*/

       /* String[] a = new String[]{"2", "b"};
        List<Map<String, Double>> abc = new ArrayList<>();
        Map<String, Double> m1 = new HashMap<>();
        m1.put("a", 3d);
        abc.add(m1);
        Map<String, Double> m2 = new HashMap<>();
        m2.put("b", 1d);
        abc.add(m2);
        Map<String, Double> m3 = new HashMap<>();
        m1.put("c", 2d);
        abc.add(m3);

        List<Map<String, Double>> collect = abc.stream().sorted(Comparator.comparing(Map::values, (o1, o2) -> {
            if (o1.isEmpty() || o2.isEmpty()) {
                return 0;
            }
            Double v1 = o1.iterator().next();
            Double v2 = o2.iterator().next();
            if (v1 < v2) {
                return 1;
            } else if (v1 > v2) {
                return -1;
            }
            return 0;
        })).collect(Collectors.toList());

        System.out.println(collect);*/

       /* Map<String,String> map = new HashMap<>();
        map.put(null,"abc");
        map.remove(null);*/


        /*String path = Demo.class.getClassLoader().getResource("phantomjs/phantomjs.exe").getPath();
        System.out.println(path);*/

     /*   String abc = "guangzhou,whtest02";
        System.out.println(abc.substring(abc.indexOf(",") + 1));
        ConcurrentHashMap<String, Long> JOBS_COMPLETE_TIME_CACHE = new ConcurrentHashMap<>();
        JOBS_COMPLETE_TIME_CACHE.put("a",1l);
        System.out.println(JOBS_COMPLETE_TIME_CACHE.contains("a"));
        System.out.println(JOBS_COMPLETE_TIME_CACHE.keySet().contains("a"));
        Map<String,String> map = new HashedMap();*/


       /*List<DemoSon> abc = new ArrayList<>();
       abc.add(new DemoSon(1l,"abc"));
       abc.add(new DemoSon(2l,"def"));*/

       /*Map<Long,DemoSon> map = new HashedMap();
       map.put(1l,abc.get(0));
       map.put(2l,abc.get(1));



       abc.forEach(demoSon -> {
           map.get(demoSon.getId()).setName("husky");
       });


        System.out.println(abc);*/

        List<Double> alarmData = new ArrayList<>();
        for (int i = 0; i < 240; i++) {
            alarmData.add(Double.valueOf(5));
        }


        DoubleSummaryStatistics stats = alarmData.stream().filter(aDouble -> aDouble != null).mapToDouble(x -> x).summaryStatistics();
        double max = stats.getMax();
        double min = stats.getMin();
        double avg = stats.getAverage();

        System.out.println(max);
        System.out.println(min);
        System.out.println(avg);


        /**
         * a -b < 0 于a<b 的不同点
         * a,b数值溢出会造成结果错误
         */
        int a = Integer.MAX_VALUE;
        System.out.println(a);
        a += 20;
        System.out.println(a);
        String str1 = "abc";
        String s = str1.intern();
        System.out.println(s == str1);

        System.out.println(SelectionKey.OP_ACCEPT);


        HashMap<Object, Object> hashMap = new HashMap<>(1);
        hashMap.put("1", "2");

        TransactionDefinition

      /*  int size = alarmData.size();
        int bashSize = 10;
        if (size > 10) {
            int f = size % 10 == 0 ? size / 10 : size / 10 + 1;
            for (int i = 0; i < f; i++) {
                int toIndex = (i + 1) * bashSize;
                if (toIndex > size) {
                    toIndex = size;
                }
                List<Object> subList =  alarmData.subList(i * bashSize, toIndex);
                System.out.println(new JSONArray(subList));
            }
        } else {
            System.out.println(alarmData);
        }*/



        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        A.node = B;
        B.node = C;
        C.node = D;
        D.node = E;
        E.node = F;



        //指向空，可以想象成位于第一个节点之前
        Node newNode = null;
        //指向第一个节点
        Node curNode = A;

        //循环中，使用第三变量事先保存curNode的后面一个节点

        while (curNode != null){
            Node tempNode = curNode.getNode();
            //把curNode反向往前指
            curNode.setNode(newNode);
            //newNode向后移动
            newNode = curNode;
            //curNode 向后移动
            curNode = tempNode;
        }

        System.out.println(newNode);



    }
}


class Node {
    public String value;
    public Node node;
    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", node=" + node +
                '}';
    }
}


class DemoSon extends Demo {

    private Long id;
    private String name;


    @Override
    public String toString() {
        return "DemoSon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public DemoSon(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public void say() {
        System.out.println("demo son");

        TreeMap<String, Integer> a = new TreeMap<>();
        a.put("3", 3);
        a.put("1", 1);
        a.put("2", 2);


        Collection<Integer> values = a.values();
        Collections.sort((List<Integer>) values);


        System.out.println(values);
    }
}





