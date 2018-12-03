package com.zk.utils;

import org.springframework.util.AntPathMatcher;

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
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        String path = "/as/ui/a";
        Boolean result = antPathMatcher.match("/**/?",path);
        System.out.println(result);
    }


}
