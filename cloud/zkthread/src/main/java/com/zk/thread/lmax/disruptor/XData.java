package com.zk.thread.lmax.disruptor;

import java.math.BigDecimal;

/**
 * @ClassName XData
 * @Description 一条交易信息
 * @Author zhangkai
 * @Date 2018/12/4 18:29
 **/
public class XData {

    private String id;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static void main(String[] args) {

        int a = 2;
        {
            a = 3;
        }
        System.out.println(a);


    }



}
