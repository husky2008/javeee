package com.zk.freemark;

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
            test.createWord("word.ftl");
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
        dataMap.put("title", "标题");
        dataMap.put("name", "张凯");
        dataMap.put("age", "3");
        dataMap.put("sex", "男");
        dataMap.put("abc","FF0000");


        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            /*List<String> img = PhantomJsTest.createImg();
            dataMap.put("img", img.get(1));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "husky" + i);
            map.put("age", i);
            map.put("sex", i % 2 == 0 ? "男" : "女");
            list.add(map);
        }
        dataMap.put("list", list);
    }
}
