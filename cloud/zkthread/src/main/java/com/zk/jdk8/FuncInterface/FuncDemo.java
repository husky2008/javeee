package com.zk.jdk8.FuncInterface;


import java.util.function.Function;

/**
 * 函数式接口编程
 * 接口中只能有一个抽象方法,但可以有多个默认的实现方法
 * 该注解不是必须的，如果一个接口符合”函数式接口”定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错
 *
 *
 * Function<T,R> T代表传入的参数,R代表返回值
 *
 */
@FunctionalInterface
public interface FuncDemo {
     String  abc();
    default void def(){

    }
    default void cfe(){

    }

}

class FuncDemoTest{
    private FuncDemo funcDemo;
    private Function<Integer,Integer> function;

    public FuncDemoTest(FuncDemo funcDemo) {
        this.funcDemo = funcDemo;
    }
    public FuncDemoTest(Function<Integer,Integer> function){this.function = function;}

    public static void main(String[] args) {

        FuncDemoTest funcDemoTest = new FuncDemoTest(() -> {
            return "abc";
        });

        System.out.println(funcDemoTest.funcDemo.abc());


        /***
         * 匿名内部类是书写形式,重写apply方法
         */
        FuncDemoTest funcDemoTest2 = new FuncDemoTest(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2 ;
            }
        });
        System.out.println(funcDemoTest2.function.apply(3));

        /**
         * 函数式接口编程,只需要实现其中的接口方法就行
         * (e)->  如果只有一个参数,()可以省略
         */
        FuncDemoTest funcDemoTest3 = new FuncDemoTest((e)->{return  e * 5;});

        System.out.println(funcDemoTest3.function.apply(2));


        Function<Integer, Integer> name = e -> e * 2;



      /*  //jdk8内部自己提供的函数式接口
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        //先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象
        int value = name.andThen(square).apply(3);   //after.apply(apply(t))
        System.out.println("andThen value=" + value);

        //先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
        int value2 = name.compose(square).apply(3);//apply(before.apply(v))
        System.out.println("compose value=" + value);*/


    }


}
