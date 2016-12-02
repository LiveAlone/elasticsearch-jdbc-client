package com.qingqing.search.demo.elasticsearch;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Created by yaoqijun on 2016/12/2.
 */
public class CreatingIndexing {

    private static final String PROPERTIES = "properties";

    public static void main(String[] args) throws Exception{
    }

    public static XContentBuilder buildMapping() throws Exception{
        XContentBuilder mapping = new XContentFactory().jsonBuilder();
        mapping.startObject().startObject("test_master_info");
        buildProperties(mapping);
        mapping.endObject().endObject();
        return mapping;
    }

    private static void buildProperties(XContentBuilder builder) throws Exception{
        builder.startObject(PROPERTIES);

        // init id
        addSimpleField("id", "long", builder);
        addSimpleField("name","string", builder);
        addSimpleField("age", "integer", builder);
        addSimpleField("grade","short", builder);
        addSimpleField("sex","byte",builder);
        addSimpleField("height", "float", builder);
        addSimpleField("weight", "double", builder);
        addSimpleField("image", "binary", builder);

        // date type
        builder.startObject("birthday").field("type", "date").field("format", "YYYY-MM-dd hh:mm:ss").endObject();


        buildAddressObject(builder);
        buildSalaryObject(builder);

        builder.endObject();
    }

    private static void buildSalaryObject(XContentBuilder builder) throws Exception{
        builder.startObject("salary")
                .field("type", "nested")
                .startObject(PROPERTIES);
        addSimpleField("basic", "double", builder);
        addSimpleField("improve", "double", builder);
        builder.endObject()
                .endObject();
    }

    private static void buildAddressObject(XContentBuilder builder) throws Exception{
        builder.startObject("address")
                .field("type","object")
                .startObject(PROPERTIES);

        addSimpleField("city", "string", builder);
        addSimpleField("province", "string", builder);

        builder.endObject()
                .endObject();
    }

    private static void addSimpleField(String name, String type, XContentBuilder builder) throws Exception{
        builder.startObject(name).field("type", type).endObject();
    }
}
