package com.zk.exception;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @ClassName TryDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/1/2 16:58
 **/
public class TryDemo {


    public static void socketTimeoutException()throws Exception{
        throw new SocketTimeoutException();
    }

    public static void socketExceptions()throws Exception{
        throw new SocketException();
    }

    public static void ioException()throws Exception{
        throw new IOException();
    }


    public static void appException(){
        throw new AppRuntimeException();
    }


    public static void main(String[] args) {


         try {
             //ioException();
             //socketTimeoutException();
             //appException();
             socketExceptions();
         }catch (IOException ie){
             System.out.println("IOException");
         } catch (InterruptedException ie){
             System.out.println("InterruptedException");
         }catch (Exception ioe){
             System.out.println("io exception");
         }






    }

















}
