package com.qingqing.search.demo;

import com.alibaba.druid.pool.ElasticSearchDruidDataSource;
import com.qingqing.search.demo.domain.Teacher;
import com.qingqing.search.demo.mapper.TeacherMapper;
import org.elasticsearch.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by yaoqijun on 2016/11/10.
 * 测试 Teacher Mapper 存储方式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TeacherMapperTest {

    @Autowired
    private ElasticSearchDruidDataSource elasticSearchDruidDataSource;

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void testMapperQueryCondition(){
        List<Teacher> teachers = teacherMapper.findAll();
        System.out.println(teachers.size());
        for (Teacher teacher : teachers) {
            System.out.println(teacher.toString());
        }
    }

    /**
     * not support execute method
     * @throws Exception
     */
    @Test
    public void testQuery() throws Exception{
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(elasticSearchDruidDataSource);
//
//        List<Map<String, Object>> results = jdbcTemplate.queryForList("select * from teacher");
//
//        for (Map<String, Object> result : results) {
//            for (Entry<String, Object> stringObjectEntry : result.entrySet()) {
//                System.out.println(stringObjectEntry.getKey() + "   " + stringObjectEntry.getValue().toString());
//            }
//        }
    }

}














