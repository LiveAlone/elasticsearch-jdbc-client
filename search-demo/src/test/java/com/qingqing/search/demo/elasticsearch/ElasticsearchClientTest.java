package com.qingqing.search.demo.elasticsearch;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;

/**
 * Created by yaoqijun on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ElasticsearchClientTest {

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
    public void testCondition(){
        System.out.println(client == null);
    }

    @Test
    public void countSearchTest(){
        CountResponse countResponse = client.prepareCount("master").execute().actionGet();
        System.out.println(countResponse.getCount());
    }

    @Test
    public void aggQuerySearchContent(){
        SearchResponse response = client.prepareSearch("master").setTypes("master_info").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(AggregationBuilders.terms("aggAge").field("age")).execute().actionGet();

        Terms aggregation = response.getAggregations().get("aggAge");
        System.out.println(aggregation.toString());
    }

    @Test
    public void searchQueryFilterTest(){
        SearchResponse response = client.prepareSearch("master")
                .setTypes("master_info")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("sex", 1))             // Query
                .setPostFilter(FilterBuilders.rangeFilter("age").from(30).to(200))   // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();
        for (SearchHit searchHitFields : response.getHits()) {
            System.out.println(searchHitFields.getSourceAsString());
        }
    }

    @Test
    public void updateIndexByFieldScript() throws Exception{
        UpdateRequest updateRequest = new UpdateRequest("master", "master_info", "2")
                .script("ctx._source.age = \"50\"");
        client.update(updateRequest).get();
    }

    @Test
    public void updateIndexContent() throws Exception{
        UpdateResponse updateResponse = client.prepareUpdate("master", "master_info", "20").setDoc("{ \"age\" : 20}")
                .setRetryOnConflict(10).setDocAsUpsert(true)
                .get();
        System.out.println(updateResponse.getGetResult());
    }

    @Test
    public void indexDeleteTestIndex() throws Exception{
        DeleteResponse deleteResponse = client.prepareDelete("master", "master_info", "1")
                .setOperationThreaded(true)
                .execute().actionGet();
        System.out.println(deleteResponse.toString());
    }

    @Test
    public void indexGetTestIndex() throws Exception{
        GetResponse getResponse = client.prepareGet("master", "master_info", "1")
                .setOperationThreaded(false)
                .execute().get();
        System.out.println(getResponse.getSourceAsString());
    }

    @Test
    public void createIndexTest() throws Exception{
        client.admin().indices().prepareCreate("test_master").execute().actionGet();
        PutMappingRequest request = Requests.putMappingRequest("test_master")
                .type("test_master_info").source(CreatingIndexing.buildMapping().string());
        PutMappingResponse response = client.admin().indices()
                .putMapping(request).actionGet();
        System.out.println(response.getHeaders());
    }

    @After
    public void after(){
        client.close();
    }

}
