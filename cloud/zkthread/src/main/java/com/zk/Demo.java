package com.zk;/**
 * Created by husky on 2018/11/16.
 */

import org.apache.commons.collections.map.HashedMap;
import org.springframework.scheduling.annotation.Scheduled;

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


        List<String> abc = new ArrayList<>();
        abc.add("1");
        abc.add("2");

        for (String s : abc) {
            if("1".equals(s)){
                abc.remove(s);
            }
        }

        System.out.println(abc);



    }
}


class DemoSon extends Demo {

    private  Long id;
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





