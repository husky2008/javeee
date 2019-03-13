package com.zk;/**
 * Created by husky on 2018/11/16.
 */

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangkai
 * @create 2018-11-16 11:04
 **/
public class Demo {


    private long a;

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
        String[] a = new String[]{"2", "b"};
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

        System.out.println(collect);

    }
}


class DemoSon extends Demo {
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





