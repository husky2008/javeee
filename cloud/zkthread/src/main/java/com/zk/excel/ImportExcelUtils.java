package com.zk.excel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Excel 文件导入,支持xls,xlsx
 * Created by zhangkai
 */
public class ImportExcelUtils<T> {

    static boolean bCut = true;
    static String DATE_F = "yyyy-MM-dd";

    /**
     * Excel 文件导入,通用上传
     *
     * @param beginRowIndex 开始遍历数据的起点,包括标题行
     * @param objClass      解析生成的实体对象clss
     * @param workbook      解析用到的WorkBook对象,HSSF,XSSF
     * @return 对象列表
     * @throws Exception
     */
    public static <T> List<T> importExcle(Class<T> objClass, Workbook workbook, int beginRowIndex, Map<Object, StringBuilder> errorMap) throws Exception {
        String msg = "导入成功";
        if (errorMap == null) errorMap = new HashMap<>();
        List<T> list = new ArrayList<>();
        try {
            //获取对象的属性值
            Field[] fields = objClass.getDeclaredFields();

            //对fields 排序和excel 表格数据一致
            int sheetNums = workbook.getNumberOfSheets();  //获取共有几个工作页
            //遍历获取数据
            for (int i = 0; i < sheetNums; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }

                Map<Integer, Field> keyFiledMap = getKeyFiledMap(sheet, fields);
                for (int j = beginRowIndex + 1; j <= sheet.getLastRowNum(); j++) {
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    //生成实例
                    T instance = objClass.newInstance();
                    StringBuilder sb = new StringBuilder();
                    for (int cellNum = 0; cellNum < fields.length; cellNum++) {
                        //如果需要解析的数据包含对应的标题,将数据解析出来
                        if (keyFiledMap.containsKey(cellNum)) {

                            Cell cell = row.getCell(cellNum);
                            Field field = keyFiledMap.get(cellNum);
                            if (cell == null || StringUtils.isBlank(cell.toString())) {
                                //若果值为空,判断该值是否为必填项,如果为必填项抛出异常
                                Boolean isNeed = field.getAnnotation(ExcelAnnotation.class).isNeed();
                                if (isNeed) {
                                    msg = field.getAnnotation(ExcelAnnotation.class).name() + ":不能为空";
                                    errorMap.put(-1, new StringBuilder().append(msg));
                                    throw new Exception(msg);
                                }
                                continue;
                            }

                            String value = null;
                            switch (cell.getCellType()) {
                                //字符串类型
                                case HSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                //公式
                                case HSSFCell.CELL_TYPE_FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                //number 类型
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Double d = cell.getNumericCellValue();
                                        SimpleDateFormat sFormat = new SimpleDateFormat(DATE_F);
                                        value = sFormat.format(HSSFDateUtil.getJavaDate(d));
                                    } else {
                                        if (bCut) {
                                            String str = new DecimalFormat("#.##").format(cell.getNumericCellValue());
                                            if (Pattern.matches("\\d+\\.0", str)) {
                                                value = StringUtils.substringBefore(str, ".");
                                            } else {
                                                value = new BigDecimal(str).toPlainString();
                                            }
                                        } else {
                                            double valueDouble = cell.getNumericCellValue();
                                            value = String.valueOf(valueDouble);

                                        }
                                    }
                                    break;
                            }

                            if (value != null) {
                                try {
                                    BeanUtils.setProperty(instance, field.getName(), value);
                                } catch (Exception e) {
                                    System.out.println(value);
                                    e.printStackTrace();
                                }
                            }
                        }
                        list.add(instance);
                        if (errorMap != null) {
                            if (sb.length() > 0) {
                                errorMap.put(j, sb);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("abc");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断一行数据是否为空行
     *
     * @param row
     * @return
     */
    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }


    /**
     * 获取实体属性对应到excel表格中的位置
     *
     * @return
     */
    public static Map<Integer, Field> getKeyFiledMap(Sheet sheet, Field[] fields) {
        Map<Integer, Field> result = new HashedMap();
        if (fields != null && sheet != null) {
            for (Field field : fields) {
                ExcelAnnotation annotation = field.getAnnotation(ExcelAnnotation.class);
                Row row = sheet.getRow(0);
                if (row != null) {
                    short lastCellNum = row.getLastCellNum();
                    for (int j = 0; j < lastCellNum; j++) {
                        String cellValue = getCellValueByCell(row.getCell(j));
                        if (annotation.name().equals(cellValue)) {
                            //如果相等,是对应标题的值
                            result.put(j, field);
                        }
                    }
                }
            }
        }
        return result;
    }


    /**
     * 获取单元格各类型值，返回字符串类型
     *
     * @param cell
     * @return
     */
    private static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue = cell.getStringCellValue().trim();
                cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    SimpleDateFormat sFormat = new SimpleDateFormat(DATE_F);
                    cellValue = sFormat.format(cell.getDateCellValue());
                } else {  //否
                    cellValue = new DecimalFormat("#.##").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型，取空串吧
                cellValue = "";
                break;
        }
        return cellValue;
    }
}
