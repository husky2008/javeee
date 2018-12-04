package com.zk.thread.lmax.disruptor;

import com.lmax.disruptor.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.*;

/**
 * @ClassName XDataMain
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/4 18:33
 **/
public class XDataMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int bufferSize = 1024;
        int threadSize = 4;



        /*
         * createSingleProducer创建一个单生产者的RingBuffer，
         * 第一个参数叫EventFactory，从名字上理解就是“事件工厂”，其实它的职责就是产生数据填充RingBuffer的区块。
         * 第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率
         * 第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略
         */
        RingBuffer<XData> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<XData>() {
            @Override
            public XData newInstance() {
                return new XData();
            }
        },bufferSize, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);

        //决定是否有供消费者来消费的Event的逻辑
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        //创建消息处理器
        BatchEventProcessor<XData> xDataBatchEventProcessor = new BatchEventProcessor<>(ringBuffer,sequenceBarrier,new XDataHandler());

        //这一部的目的是让RingBuffer根据消费者的状态    如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(xDataBatchEventProcessor.getSequence());


        executorService.submit(xDataBatchEventProcessor);

        Future<Void> future = executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for (int i = 0; i < 1000; i++) {
                    seq = ringBuffer.next();//占个坑 --ringBuffer一个可用区块
                    ringBuffer.get(seq).setPrice(new BigDecimal("0.12"));//给这个区块放入 数据  如果此处不理解，想想RingBuffer的结构图
                    ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见
                }
                return null;
            }
        });

        future.get();
        Thread.sleep(1000);//等上1秒，等消费都处理完成
        xDataBatchEventProcessor.halt();//通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executorService.shutdown();//终止线程

    }


}
