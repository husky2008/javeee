package com.zk.design;/**
 * Created by husky on 2018/11/15.
 */

/**
 * 简单工厂模式
 *  所有的对象都在一个工厂里面生成
 * @author zhangkai
 * @create 2018-11-15 20:06
 **/
public class SimpleFactoryDemo {
    public static Car getCar(int type) {
        switch (type) {
            case 1:
                return new BMW();
            case 2:
                return new AODI();
            case 3:
                return new FORD();
            default:
                return null;
        }
    }


    public static void main(String[] args) {

        SimpleFactoryDemo.getCar(1).desc();

    }
}


/**
 * 接口
 */
interface Car {
    void desc();
}

class BMW implements Car {
    @Override
    public void desc() {
        System.out.println("我是宝马");
    }
}

class AODI implements Car {
    @Override
    public void desc() {
        System.out.println("我是奥迪");
    }
}

class FORD implements Car {
    @Override
    public void desc() {
        System.out.println("我是福特");
    }
}
