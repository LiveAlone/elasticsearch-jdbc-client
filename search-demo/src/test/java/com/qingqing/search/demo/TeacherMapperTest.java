package com.qingqing.search.demo;

import com.alibaba.druid.pool.ElasticSearchDruidDataSource;
import com.qingqing.search.demo.domain.ActivityBase;
import com.qingqing.search.demo.domain.Teacher;
import com.qingqing.search.demo.mapper.ActivityMapper;
import com.qingqing.search.demo.mapper.TeacherMapper;
import org.elasticsearch.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by yaoqijun on 2016/11/10.
 * 测试 Teacher Mapper 存储方式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TeacherMapperTest {

//    @Autowired
//    private ElasticSearchDruidDataSource elasticSearchDruidDataSource;

    @Autowired
    private TeacherMapper teacherMapper;


    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void testCondition(){
        List<ActivityBase> activityBases = activityMapper.findAll();
        System.out.println(activityBases.size());
    }

    @Test
    public void testMapperQueryCondition(){
        List<Teacher> teachers = teacherMapper.findAll();
        System.out.println(teachers.size());
//        JSONUtils.toJSONString()
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

    /**
     * 测试数据源查询的方式
     */
    @Test
    public void testFromDataSource() throws Exception{
//        Connection connection = elasticSearchDruidDataSource.getConnection();
//        PreparedStatement ps = connection.prepareStatement("select * from teacher");
//        List<String> columns = Lists.newArrayList("id", "name", "age", "salary");
//        ResultSet resultSet = ps.executeQuery();
//        while (resultSet.next()) {
//            System.out.println("-------------------------------");
//            for (String column : columns) {
//                System.out.print("  " + column + " is : " + resultSet.getObject(column).toString());
//            }
//            System.out.println("");
//        }
    }

}













