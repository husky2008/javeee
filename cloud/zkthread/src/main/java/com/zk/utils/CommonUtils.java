package com.zk.utils;

import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName CommonUtils
 * @Description 整理由框架提供的项目中会经常用到的工具类
 * @Author zhangkai
 * @Date 2018/11/30 15:52
 **/
public class CommonUtils {


    public static void main(String[] args) {

        /**
         * 由spring 提供,专门用于uri配置的工具类
         *   ?（匹配任何单字符），*（匹配0或者任意数量的字符），**（匹配0或者更多的目录）
         */
//        AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//        String path = "/as/ui/a";
//        Boolean result = antPathMatcher.match("/**/?", path);
//        System.out.println(result);


        String abc = "12300.1";
        System.out.println(subZeroAndDot(abc));

        List<String> param = new ArrayList<>();
        param = get(param);
        System.out.println(param);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("abc");
                System.gc();
            }
        }, 1000*10);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("abc");
                System.gc();
            }
        }, 1000*20);

        System.out.println("abc1");


    }


    /**
     * 如果小数点之后是0,则去掉多余的0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static List<String> get(
            List<String> param) {
        if (param == null) {
            param = new ArrayList<>();
        }
        param.add("123");
        return param;
    }


}
