package com.zk.excel;

import java.lang.annotation.*;

/**
 * 注解字段用于排序
 * Created by husky on 2017/5/18.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
    String name() default "";  //列名

    int num() default 0;  //用于排序的字段

    int width() default 0;  //长度,字符长度的限定

    boolean skip() default false;  //是否忽略该字段

    boolean isNeed() default false;  //是否必填字段
}
