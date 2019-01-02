package com.zk.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegexDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/22 16:22
 **/
public class RegexDemo {


    public static void main(String[] args) {
        Pattern PATTERN = Pattern.compile("\\w+[->\\w+]*");
        Matcher matcher = PATTERN.matcher("100 - cpu -> idle");
        System.out.println(matcher.matches());
        while (matcher.find()) {
            String key = matcher.group();
            System.out.println(key);
        }

    }
}
