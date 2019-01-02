package com.zk.jdk8;

/**
 * 在jdk8中新引入了在接口中定义默认方法
 * 解决问题：接口新增方法,不影响现有实现的架构
 *
 * 在类中可以直接调用
 * 问题1:如果实现的2个接口有相同的方法,需要在实现类中重写对应的方法,并指定要用那个接口中的默认方法B.super.say();
 * 问题2：如果实现类重写了接口的方法,那么会调用实现类中重写过的方法,不会调用接口中的默认方法。
 *
 * @ClassName DefaultMethodDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/5 14:17
 **/
public class DefaultMethodDemo {

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.abc());
        demo.say();

    }

}


/**
 * 如果实现的2个接口有相同的方法,需要在实现类中重写对应的方法,并指定要用那个接口中的默认方法B.super.say();
 */
class Demo implements A,B {

    @Override
    public void say() {
        B.super.say();
    }

}


interface A {

    default int abc() {
        return 1;
    }

    default void say() {
        System.out.println("hello");
    }
}

interface B {
    default void say() {
        System.out.println("hello B");
    }
}
