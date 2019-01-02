package com.zk.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * ScriptEngineManager 去执行脚本
 * @ClassName ScriptDemo
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/12/11 15:15
 **/
public class ScriptDemo {


    public static void main(String[] args) throws ScriptException {


        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javaScript = scriptEngineManager.getEngineByName("JavaScript");
        System.out.println(javaScript.eval("100 -90.10"));

    }




}
