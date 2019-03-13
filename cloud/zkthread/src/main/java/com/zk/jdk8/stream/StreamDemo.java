package com.zk.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *JDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下(Consumer)
 * filter:过滤流
 * map:把一种类型的流转换为另外一种类型的流
 * flapMap：拆解流，将流中每一个元素拆解成一个流
 * forEach:内部传递一个Consumer对象
 *
 *
 * @ClassName StreamDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/24 11:28
 **/
public class StreamDemo {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Stream<Integer> stream = list.stream();
        /**
         * count()： 计算数量
         * map:function
         *
         *
         */

        //long count = stream.filter(integer -> integer > 2).count();
        //stream.filter(integer -> integer >= 1).forEach(System.out::print);
        //stream.map(integer -> integer * 2 + "").filter(integer -> integer.equals("abc")).forEach(System.out::println);


        List<Integer> collect = stream.filter(integer -> integer >= 2).collect(Collectors.toList());
        System.out.println(collect);

        List<String> list2 = new ArrayList<>();
        list2.add("abcf");
        list2.add("def");
        list2.add("dddddd");
        list2.forEach(StreamDemo::abc);
        StreamDemo.fuck(StreamDemo::abc);


        /**
         * 流的创建
         */
        IntStream stream1 = Arrays.stream(new int[]{1, 2, 3});  //通过 Arrays.stream()创建流
        Stream<String> stream2 = Stream.of("1", "a","3"); //Stream.of()创建流
        Stream.generate(() ->new Student()).limit(20); //


        /**
         * 流的操作
         *  map：转换流，将一种类型的流转换为另外一种流
         *  filter：过滤流，过滤流中的元素
         *  flapMap：拆解流，将流中每一个元素拆解成一个流
         */
        stream1.map(operand -> operand + 1).forEach(System.out::println);
        //stream2.map(x -> Integer.parseInt(x)).forEach(System.out::println);


        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        // Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);


    }


    public static  void def(Function f){

    }

    public static  void fuck(Consumer<? super String> f){
        System.out.println("fuck");
    }

    public static void abc(String string){
        System.out.println("abc");
    }










}
