package com.zk.design;/**
 * Created by husky on 2018/11/15.
 */

/**
 *  工厂方法模式
 *  针对不同的类型创建不同的工厂,通过具体的工厂去生产
 * @author zhangkai
 * @create 2018-11-15 20:25
 **/
public class FactoryMethodDemo {
    public static void main(String[] args) {
        IFactory factory = new HDFactory();
        factory.create();
    }
}


interface Water {
    void desc();
}

class NongF implements Water {
    @Override
    public void desc() {
        System.out.println("农夫山泉");
    }
}

class HDBQ implements Water {
    @Override
    public void desc() {
        System.out.println("恒大冰泉");
    }
}

interface IFactory {
    Water create();
}

class NFFactory implements IFactory {
    @Override
    public Water create() {
        return new NongF();
    }
}

class HDFactory implements IFactory {
    @Override
    public Water create() {
        return new HDBQ();
    }
}
