package com.zk.excel;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.List;

/**
 * @ClassName ExcelImportTests
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/4/16 17:53
 **/
public class ExcelImportTests {


    public static void main(String[] args) throws Exception {


        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new File("D:\\abc.xlsx"));
        List list = ImportExcelUtils.importExcle(ExcelVo.class, xssfWorkbook, 1, new HashedMap());
        for (Object o : list) {
            System.out.println(o);
        }


    }


}
