package com.zk.j2ee.enumdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 枚举中的对象本来就是单例的
 *
 * @ClassName EnumDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/13 11:47
 **/
public class EnumDemo {

    public static Map<A, String> map = new HashMap<>();

    enum A {
        a((str) -> {
            return str.contains("a");
        }),
        b((str) -> {
            return str.contains("b");
        });

        Function<String, Boolean> fun;

        public Function<String, Boolean> getFun() {
            return fun;
        }

        A(Function<String, Boolean> fun) {
            this.fun = fun;
        }
    }

    public static void main(String[] args) throws Exception {

       /* System.out.println(map.put(A.a,"1"));
        System.out.println(map.put(A.a,"1"));
        System.out.println(map.put(A.b,"1"));
        System.out.println(map);
        System.out.println(A.a == A.a);

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = s.format(new Date(1544716875001l));
        System.out.println(str);
        Date parse = s.parse("2018-12-16 00:00:00");
        System.out.println(parse.getTime());*/

        Map<Integer, String> map = new HashMap<>();


        map.put(1, "1");
        map.put(2, "1");
        map.put(3, "1");
        map.put(4, "1");
        map.put(5, "1");

       /* for(Integer id :new ArrayList<>(map.keySet())){
            if(id == 2){
                map.remove(id);
            }
        }*/

       map.clear();

        System.out.println(map);


    }

    public static Boolean isContain(A a, String type) {
        if (map.containsKey(a)) {
            return true;
        } else {
            map.put(a, type);
            return false;
        }
    }


}
