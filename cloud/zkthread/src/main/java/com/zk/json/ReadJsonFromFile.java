package com.zk.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName ReadJsonFromFile
 * @Description TODO
 * @Author zhangkai
 * @Date 2019/5/23 17:12
 **/
public class ReadJsonFromFile {

    public static void main(String[] args) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(Class.class.getResourceAsStream("/a"), "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        JSONObject jsonObject = JSON.parseObject(sb.toString());
        JSONObject nodes = jsonObject.getJSONObject("PBS").getJSONObject("nodes");
        int numNode = nodes.getIntValue("num");

        int cpuT = 0;
        int cpuA = 0;
        for (int i = 0; i < numNode; i++) {
            JSONArray jobArr = nodes.getJSONArray(String.valueOf(i));
            int cpuAlloc = jobArr.getInteger(3);
            int cpuTotal = jobArr.getInteger(4);


            String state = jobArr.getString(1);

            cpuT = cpuT + cpuTotal;

            cpuA = cpuA + cpuAlloc;


        }
        System.out.println("总的:" + cpuT);
        System.out.println("占用的:" + cpuA);


        System.out.println(sb.toString());

    }


}
