package com.zk.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ExcelVo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/4/16 17:56
 **/
public class ExcelVo {

    @ExcelAnnotation(num = 1, name = "用户名")
    private String customerName;
    @ExcelAnnotation(num = 2, name = "部门1")
    private String customerNum;
    @ExcelAnnotation(num = 3, name = "性别")
    private String customerType;
    @ExcelAnnotation(num = 4, name = "用户组")
    private String accountBank;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    @Override
    public String toString() {
        return "ExcelVo{" +
                "customerName='" + customerName + '\'' +
                ", customerNum='" + customerNum + '\'' +
                ", customerType='" + customerType + '\'' +
                ", accountBank='" + accountBank + '\'' +
                '}';
    }

    public static void main(String[] args) {
        BigDecimal duration = new BigDecimal("2.00");
        long time = duration.multiply(new BigDecimal("3600000")).longValueExact();
        System.out.println(time);
        System.out.println(Arrays.asList("/sdfdsdfcdfg/abc/".split("/")));
        List<String> abc = new ArrayList<>();
        abc.add("c");
        abc.add("d");
        abc.add("f");
        abc.add("c");


        System.out.println(String.join(",", abc));
        String str = "abc";
        for (int i = 0; i < 10; i++) {
            String b = str.replaceAll("b", "1");
            System.out.println(b);
        }
        System.out.println(str);

    }


}
