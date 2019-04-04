package com.zk.freemark;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zk.dom4j.StringDemo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过freemark 模板引擎,生成word文档
 *
 * @ClassName FreeMarkWordDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/20 11:28
 **/
public class FreeMarkWordDemo {


    private Configuration configuration = null;

    public FreeMarkWordDemo() {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
    }


    public static void main(String[] args) {
        FreeMarkWordDemo test = new FreeMarkWordDemo();
        try {
            String ftl = StringDemo.createFtl();
            test.createWord("cluster.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createWord(String ftl) {
        Map<String, Object> dataMap = new HashMap<>();
        getData(dataMap);
        Template t = null;
        try {
            //configuration.setClassForTemplateLoading(this.getClass(), "/freemark");//模板文件所在路径
            configuration.setDirectoryForTemplateLoading(new File("D:\\ftl"));
            if (StringUtils.isBlank(ftl)) ftl = "freemark.ftl";
            t = configuration.getTemplate(ftl); //获取模板文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outFile = new File("D:/outFile" + Math.random() * 10000 + ".doc"); //导出文件
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out); //将填充数据填入模板文件并输出到目标文件
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getData(Map<String, Object> dataMap) {

       /* Map<String,Object> user = new HashMap<>();
        user.put("description", "描述");
        user.put("name", "user011");
        user.put("previous", "23881");
        user.put("current", "23331");
        user.put("abc","FF0000");*/
        String color = "00B050";   //FF0000

        JSONArray array = new JSONArray();
        for (int i = 0; i < 5; i++) {
            JSONObject user = new JSONObject();
            user.put("description", "描述j");
            user.put("name", "user011j");
            user.put("previous", "23881j");
            user.put("current", "23331j");
            user.put("abc","FF0000j");
            if(i/2 == 0){
                color = "FF0000";
            }else{
                color="00B050";
            }
            user.put("color",color);
            user.put("value",i);
            array.add(user);
        }
        dataMap.put("userlist",array);

        JSONObject report = new JSONObject();
        report.put("time",-1);
        dataMap.put("report",report);





        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            /*List<String> img = PhantomJsTest.createImg();
            dataMap.put("img", img.get(1));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
