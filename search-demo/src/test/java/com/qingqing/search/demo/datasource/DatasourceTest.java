package com.qingqing.search.demo.datasource;

import com.alibaba.druid.pool.ElasticSearchDruidDataSource;
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
 * Created by yaoqijun.
 * Date:2016-11-23
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DatasourceTest {

    @Autowired
    private ElasticSearchDruidDataSource elasticSearchDruidDataSource;

    /**
     * 测试数据源查询的方式
     */
    @Test
    public void testFromDataSource() throws Exception{
        Connection connection = elasticSearchDruidDataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from teacher");
        List<String> columns = Lists.newArrayList("id", "name", "age", "salary");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            System.out.println("-------------------------------");
            for (String column : columns) {
                System.out.print("  " + column + " is : " + resultSet.getObject(column).toString());
            }
            System.out.println("");
        }
        ps.close();
        connection.close();
    }
}
