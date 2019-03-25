package com.zk.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * @ClassName Dom4jDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/3/21 19:45
 **/
public class Dom4jDemo {

    public static void main(String[] args) throws Exception {

        //第一步：获得一个解析器
        SAXReader saxreader = new SAXReader();

        //第二步：指定解析的XML文件
        Document document=saxreader.read(new File("D:/demo/javeee/cloud/zkthread/target/classes/freemark/body.xml"));
        Iterator<Element> xmlData = document.getRootElement().elementIterator("part");
        while (xmlData.hasNext()){
            Element next = xmlData.next();
            if("/word/document.xml".equals(next.attributeValue("name"))){
                //数据元素节点
                Element element = next.element("xmlData").element("document").element("body").element("job_start");
                Iterator<Element> elementIterator = element.elementIterator();
                while (elementIterator.hasNext()){
                    Element next1 = elementIterator.next();
                    Attribute attribute = next1.attribute("id");
                    if(attribute != null && "job_start".equals(attribute.getValue())){
                        System.out.println(next1);
                    }
                }
            }
        }



    }


}
