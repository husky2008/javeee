package com.zk.reference;

import java.lang.ref.WeakReference;

/**
 * 1.WeakReference对象的特性就是代码运行一段时间，如果碰到gc，WeakReference指向的对象会被gc回收。
 * WeakReference的一个特点是它何时被回收是不可确定的, 因为这是由GC运行的不确定性所确定的.
 * 所以, 一般用weak reference引用的对象是有价值被cache, 而且很容易被重新被构建, 且很消耗内存的对象.
 *
 * 2.另一种常用的引用类型介绍：SoftReference
 * soft reference和weak reference一样, 但被GC回收的时候需要多一个条件: 当系统内存不足时, soft reference指向的object才会被
 * 回收. 正因为有这个特性, soft reference比weak reference更加适合做cache objects的reference. 因为它可以尽可能的retain
 * cached objects, 减少重建他们所需的时间和消耗.
 *
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/10/24 9:33
 **/
public class MyReference {
    public static void main(String[] args) {

        Car car = new Car();
        WeakReference<Car> weakReference = new WeakReference(car);
        int i =0 ;
        while (true){
            if(weakReference.get() != null){
               i++;
                System.out.println("循环次数=" + i+"，实体="+weakReference.get());
                if(i == 10000){
                    System.gc();
                }
            }else{
                System.out.println("被垃圾回收---");
                break;
            }
        }
        System.out.println(car);

    }

}

class Car {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
