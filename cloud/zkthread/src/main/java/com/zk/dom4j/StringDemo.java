package com.zk.dom4j;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName StringDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/22 11:22
 **/
public class StringDemo {


    public static void main(String[] args) throws Exception {


    }

    public static String createFtl() throws Exception {
        StringBuilder sb = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(new File("D:/demo/javeee/cloud/zkthread/target/classes/freemark/header.txt"));
        String header = IOUtils.toString(fileInputStream, "UTF-8");
        FileInputStream fileInputStream1 = new FileInputStream(new File("D:/demo/javeee/cloud/zkthread/target/classes/freemark/content.txt"));
        String content = IOUtils.toString(fileInputStream1, "UTF-8");
        String end = IOUtils.toString(new FileInputStream(new File("D:/demo/javeee/cloud/zkthread/target/classes/freemark/end.txt")), "UTF-8");
        String xml = sb.append(header).append(content).append(end).toString();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\ftl\\user1.ftl"));
        //IOUtils.write(xml.getBytes(),fileOutputStream);
        IOUtils.write(xml,fileOutputStream,"UTF-8");
        fileOutputStream.close();
        return "user1.ftl";
    }


}
