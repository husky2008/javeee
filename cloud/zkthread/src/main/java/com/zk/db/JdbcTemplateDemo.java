package com.zk.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName JdbcTemplateDemo
 * @Description 普通jdbc对数据库的操作,参数的配置采用站位符的形式,不方便维护.对参数顺序有要求
 * @Author zhangkai
 * @Date 2018/11/30 14:32
 **/

@Component
public class JdbcTemplateDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Integer insert(){
        String sql = "insert into t_user (name,age,sex,birth_date,create_time) values (?,?,?,?,?)";

        /**
         * 根据站位符号和顺序来设定值
         * jdbcTemplate.update(sql,new Object[]{"husky", 12, 1, new Date(), new Date()});
         */
        ArgumentPreparedStatementSetter argumentPreparedStatementSetter = new ArgumentPreparedStatementSetter(new Object[]{"husky", 12, 1, new Date(), new Date()});
        int index = jdbcTemplate.update(sql,argumentPreparedStatementSetter);
        return  index;
    }
}
