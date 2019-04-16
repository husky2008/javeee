package com.zk.excel;

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
}
