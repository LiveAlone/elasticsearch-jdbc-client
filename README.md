### elastic search jdbc java client 
es SQL 查询的支持， jdbc 数据源配置， mybatis 配置支持。

#### 项目结构
##### search-client
- ```com.alibaba.druid.pool.result```  search 查询结果集的扩展
- ```com.alibaba.druid.pool```  扩展druid 数据源，对 Es datasource 的支持
##### search-demo
- demo 代码示例， 测试 search-client 内容
- ```SqlSupportTest```  es 对SQL 的种类测试。
####  es测试数据
- index: teacher , type: teacher_info
- mapping info 
```
{
    "teacher": {
        "mappings": {
            "teacher_info": {
                "properties": {
                    "age": {
                        "type": "integer"
                    },
                    "id": {
                        "type": "long"
                    },
                    "name": {
                        "type": "string",
                        "index": "not_analyzed"
                    },
                    "salary": {
                        "type": "nested",
                        "properties": {
                            "basic": {
                                "type": "double"
                            },
                            "improve": {
                                "type": "double"
                            }
                        }
                    }
                }
            }
        }
    }
}
```
- 测试数据内容 _bulk
```
{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "1" } }
{"id": "1", "name": "xiaoming", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "2" } }
{"id": "2", "name": "xiaohong", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "3" } }
{"id": "3", "name": "xiaohua", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "4" } }
{"id": "4", "name": "zhanghan", "age": 20, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "5" } }
{"id": "5", "name": "zhetan", "age": 20, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "6" } }
{"id": "6", "name": "zhicao", "age": 20, "salary": [{"basic": 50,"improve": 20},{"basic": 90,"improve": 20},{"basic": 12,"improve": 24}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "7" } }
{"id": "7", "name": "shaolong", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "8" } }
{"id": "8", "name": "jinhua", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 10,"improve": 210},{"basic": 100,"improve": 201}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "9" } }
{"id": "9", "name": "xiaohuang", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 100,"improve": 220},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "10" } }
{"id": "10", "name": "qijun", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 100,"improve": 230},{"basic": 100,"improve": 10}]}
```