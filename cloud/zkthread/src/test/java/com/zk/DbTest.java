package com.zk;

import com.zk.db.JdbcTemplateDemo;
import com.zk.db.NamedParamJdbcTemplateDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName DbTest
 * @Description TODO
 * @Author zhangkai
 * @Date 2018/11/30 14:37
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbTest {

    @Autowired
    private JdbcTemplateDemo jdbcTemplateDemo;



    @Autowired
    private NamedParamJdbcTemplateDemo namedParamJdbcTemplateDemo;


    @Test
    public void insert() {

        //System.out.println(jdbcTemplateDemo.insert());
        System.out.println(namedParamJdbcTemplateDemo.insert());


    }


}
