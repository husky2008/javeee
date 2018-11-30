package com.zk.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName DemoAspect
 * @Description 定义切面
 * @Author zhangkai
 * @Date 2018/11/29 18:11
 **/
@Aspect
@Component
public class AspectDemo {


    /**
     * 定义切入点
     * 1.@annotation 包含指定注解的方法
     * @Pointcut("@annotation(com.zk.aspect.AspectAnnotation)")
     *
     * 2.excution 范围更宽的需要拦截切入点
     * @Pointcut("execution(* com.zk.aspect.IService+.*(Object+))")
     *  execution(* com.zk.aspect..*.*(..))")
     *  2.1 第一个 * 表示方法的返回类型  *代表所有
     *  2.2 .. 代表 com.zk.aspect 包及其子包
     *  2.3 第二个*代表类   *代表所有类
     *  2.4 *(..) 表示方法  *表示所有的方法名, (..) 表示任意的参数
     *
     *  execution(* com.zk.aspect..*.say*(..))") 表示say开头的方法
     *  execution(* com.zk.aspect..As*.say*(..)) 表示 As开头的类,say开头的方法
     *  execution(* com.zk.aspect.IService.*(..)) IService接口中的方法
     *  execution(* com.zk.aspect.IService+.*(..)) IService接口中的方法及接口实现类中自己的方法
     *  execution(* com.zk.aspect.IService+.*(String,int)) 指定特定的参数类型
     */
    @Pointcut("execution(* com.zk.aspect.IService+.*(Object+))")
    public void autoLog(){
    }

    @Before("autoLog()")
    public void before(){
        System.out.println("before");
    }


    @After("autoLog()")
    public void after(){
        System.out.println("after");
    }


    @Around("autoLog()")
    public void around(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        System.out.println(target);
        System.out.println(args[0]);
        System.out.println("Around");
    }









}
