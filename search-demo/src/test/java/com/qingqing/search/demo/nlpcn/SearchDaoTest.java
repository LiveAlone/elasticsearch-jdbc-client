package com.qingqing.search.demo.nlpcn;

import com.alibaba.druid.pool.result.ObjectResult;
import com.alibaba.druid.pool.result.ObjectResultsExtractor;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;

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

    @Test
    public void testAggsBasic() throws Exception{
        String query = "select min(age) as minCount from master";
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
        String query = "select * from master where id = 9";
        SearchDao searchDao = new SearchDao(client);
        QueryAction queryAction = searchDao.explain(query);
        Object execution = QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(), queryAction);
        ObjectResult result =
                new ObjectResultsExtractor(false, false).extractResults(execution, true, ",");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(result));
    }

    @Test
    public void testDeleteDao() throws Exception{
        String query = "delete from master where age = 20";
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
