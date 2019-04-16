package com.zk.excel;


import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * 排序
 * 根据Annotaion 注解的属性值进行排序
 * 方便excel 取值
 * Created by zhangkai on 2017/5/18.
 */


public class ExcelComparator implements Comparator<Object> {


    @Override
    public int compare(Object o1, Object o2) {
        Field field1 = (Field) o1;
        Field field2 = (Field) o2;
        ExcelAnnotation annotation1 = field1.getAnnotation(ExcelAnnotation.class);
        ExcelAnnotation annotation2 = field2.getAnnotation(ExcelAnnotation.class);
        if (annotation1 == null || annotation2 == null) {
            return 0;
        }
        return annotation1.num() - annotation2.num();
    }
}
