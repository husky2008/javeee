package com.zk.db;

import com.zk.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName NamedParamJdbcTemplateDemo
 * @Description 命名参数的形式,写sql语句
 * @Author zhangkai
 * @Date 2018/11/30 15:00
 **/

@Component
public class NamedParamJdbcTemplateDemo {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public int insert(){
        String sql = "insert into t_user (name,age,sex,birth_date,create_time) values (:name,:age,:sex,:birthDate,:createTime)";

        /**
         * map 定义参数类型
         *  Map<String,Object> param = new HashMap<>();
         *         param.put("name","husky");
         *         param.put("age",1);
         *         param.put("sex",1);
         *         param.put("birthDate",new Date());
         *         param.put("createTime",new Date());
         *         int a = namedParameterJdbcTemplate.update(sql,param);
         */


        /**
         * 将实体类包装成参数
         */
        User user = new User();
        user.setName("snake");
        user.setAge(12);
        user.setSex((short) 23);
        user.setBirthDate(new Date());
        user.setCreateTime(new Date());

        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,param,keyHolder);

        int a  = keyHolder.getKey().intValue();

        return  a;
    }



}
