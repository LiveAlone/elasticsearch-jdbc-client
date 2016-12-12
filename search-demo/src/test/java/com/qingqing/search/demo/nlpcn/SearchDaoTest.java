package com.qingqing.search.demo.nlpcn;

import com.alibaba.druid.pool.ElasticSearchDruidDataSource;
import com.alibaba.druid.pool.result.ObjectResult;
import com.alibaba.druid.pool.result.ObjectResultsExtractor;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.jackson.core.JsonParser;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugin.nlpcn.QueryActionElasticExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.query.QueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;
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
public class SearchDaoTest {

    private Client client;

    @Before
    public void before() throws Exception{

        // init es client
        Settings settings = ImmutableSettings.settingsBuilder().put("client.transport.ignore_cluster_name", true)
                .put("client.transport.sniff", true).build();
        this.client = new TransportClient(settings).addTransportAddresses(
                new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

    }

    /**
     * 通过搜索测试NestedType 聚合的操作方式
     */
    @Test
    public void testAggsSearchDao() throws Exception{
        String query = "select count(id) as idCount from teacher group by age";

        SearchDao searchDao = new SearchDao(client);

        QueryAction queryAction = searchDao.explain(query);
        Object execution = QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(), queryAction);


        ObjectResult result =
                new ObjectResultsExtractor(false, false).extractResults(execution, true, ",");


        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));
    }

    @Test
    public void testSearchDao() throws Exception{
//        String query = "select id, name, age from teacher where id = 1";
//        String query = "select salary.basic, salary.improve from master where id = 1";
        String query = "select * from master where nested(salary.basic) = 100";

        SearchDao searchDao = new SearchDao(client);

        QueryAction queryAction = searchDao.explain(query);
        Object execution = QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(), queryAction);


        ObjectResult result =
                new ObjectResultsExtractor(false, false).extractResults(execution, true, ",");


        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));
    }

    @After
    public void after(){
        client.close();
    }

}
