package com.zk.j2ee;


/**
 * 多继承状态,方法的调用顺序
 * 如果当前类没有实现接口的方法,调用的时候回去父类中去找,如果父类中没有去父类的父类中去找,只到找到为止。
 * 一层一层向上调,如果找到立即调用,然后返回
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/7 16:01
 **/
public class MethodInvokeSort extends A2 {
    public static void main(String[] args) {
        MethodInvokeSort a = new MethodInvokeSort();
        System.out.println(a.say());
    }
}


interface SA {
    String say();
}

class A1 implements SA {
    @Override
    public String say() {
        return "abc";
    }
}

class A2 extends A1 {
    @Override
    public String say() {
        return "bcd";
    }
}
