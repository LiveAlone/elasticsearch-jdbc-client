package com.qingqing.search.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;
import org.elasticsearch.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by yaoqijun on 2016/11/10.
 * 测试 1.4.9 对Sql 的支持方式
 */
public class SqlSupportTest {

    public static final String url  = "result:elasticsearch://localhost:9300/teacher";

    public static void main(String[] args) throws Exception {

        EsJdbc esJdbc = new EsJdbc(url);

//        selectTest(esJdbc);

//        testSelectInner(esJdbc);

//        testWhere(esJdbc);

//        testWhereInner(esJdbc);

//        testOrderBy(esJdbc);

//        testAggExe(esJdbc);

        esJdbc.close();
    }

    public static void testAggExe(EsJdbc esJdbc) throws Exception{
        resultOutput(Lists.<String>newArrayList(
                "age", "countNum", "basicNum", "maxNum","minNum","sumNum"
        ), esJdbc.resultSet("select age, \n" +
                "count(name) as countNum,\n" +
                "avg(salary.basic) as basicNum,\n" +
                " max(salary.improve) as maxNum,\n" +
                "min(salary.improve) as minNum,\n" +
                "sum(salary.basic) as sumNum\n" +
                " from teacher group by age "));
    }

    public static void testGroupBy(EsJdbc esJdbc) throws Exception{
        resultOutput(Lists.newArrayList("id", "name", "salary"),
                esJdbc.resultSet("select count(1) from teacher group by age"));
    }

    public static void testOrderBy(EsJdbc esJdbc) throws Exception{
        resultOutput(Lists.newArrayList("id", "name", "salary"),
                esJdbc.resultSet("select * from teacher order by id desc"));
    }

    public static void testWhereInner(EsJdbc esJdbc) throws Exception{
        resultOutput(Lists.newArrayList("id", "name", "salary"),
                esJdbc.resultSet("select id, name, salary from teacher where salary.basic = 50 and salary.improve = 20"));
    }

    public static void testWhere(EsJdbc esJdbc) throws Exception{


        resultOutput(Lists.newArrayList("id", "name", "salary"),
                esJdbc.resultSet("select id, name, salary from teacher where name = 'zhanghan'"));
    }

    /**
     * select id, name, salary.basic from teacher
     */
    public static void testSelectInner(EsJdbc esJdbc) throws Exception{
        resultOutput(Lists.newArrayList("id", "name", "salary"), esJdbc.resultSet("select id, name, salary.basic from teacher"));
    }

    /**
     * select * from teacher
     * @param esJdbc
     * @throws Exception
     */
    public static void selectTest(EsJdbc esJdbc) throws Exception{

        resultOutput(Lists.newArrayList("id", "name", "age", "salary"), esJdbc.resultSet("select * from teacher"));
    }

    public static void resultOutput(List<String> columns, ResultSet set) throws Exception{

        while (set.next()){

            for (String column : columns) {
                System.out.print("  " + column + " is : " + set.getObject(column).toString());
            }

            System.out.println("");
        }

        set.close();
    }

    private static class EsJdbc{

        DruidDataSource dds;

        Connection connection;

        PreparedStatement ps;

        public EsJdbc(String url) throws Exception{
            Properties properties = new Properties();
            properties.put("url", url);
            dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
            dds.setInitialSize(1);
            connection = dds.getConnection();
        }

        public ResultSet resultSet(String sql) throws Exception{

            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            return resultSet;
        }

        public void close() throws Exception{
            ps.close();
            connection.close();
            dds.close();
        }

    }

}
