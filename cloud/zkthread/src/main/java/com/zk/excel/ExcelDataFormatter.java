package com.zk.excel;/**
 * Created by husky on 2017/5/23.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * date formatter
 *
 * @author zhangkai
 * @create 2017-05-23 20:15
 **/
public class ExcelDataFormatter {

    private Map<String, Map<String, String>> formatter = new HashMap<>();

    public void set(String key, Map<String, String> map) {
        formatter.put(key, map);
    }

    public Map<String, String> get(String key) {
        return formatter.get(key);
    }
}
