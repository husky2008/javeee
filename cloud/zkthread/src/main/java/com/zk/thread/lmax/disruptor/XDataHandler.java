package com.zk.thread.lmax.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * 消费者,实现EventHandler 接口,在接口中对数据进行处理
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/4 18:31
 **/
public class XDataHandler implements EventHandler<XData>, WorkHandler<XData> {
    @Override
    public void onEvent(XData xData, long l, boolean b) throws Exception {
        this.onEvent(xData);
    }

    @Override
    public void onEvent(XData xData) throws Exception {
          xData.setId(UUID.randomUUID().toString());
        System.out.println(xData.getId());
    }
}
