package com.qingqing.search.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.qingqing.search.demo.domain.Student;
import com.qingqing.search.demo.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by yaoqijun.
 * Date:2016-11-14
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void testStudentCondition(){
        List<Student> studentList = studentMapper.findAll();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

    /**
     * 测试通过datasource 方式配置
     */
    @Test
    public void testByDatasource() throws Exception{
        System.out.println(dataSource != null);

        Connection conn =  dataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("select * from student");

        List<String> columns = Lists.newArrayList("id", "age", "name", "grade");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            for (String column : columns) {
                System.out.print("  " + column + " : " + String.valueOf(resultSet.getObject(column)));
            }
            System.out.println("");
        }
    }
}
