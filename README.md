### elastic search jdbc java client 
es SQL 查询的支持， jdbc 数据源配置， mybatis 配置支持。

#### 项目结构
##### search-client
- ```com.alibaba.druid.pool.result```  search 查询结果集的扩展
- ```com.alibaba.druid.pool```  扩展druid 数据源，对 Es datasource 的支持
- ```com.qingqing.search.client.handler``` ES 不同类型数据Handler(测试对应的Handler 修改方式)

 ```
 1 array filed type, List 数组类型的装换方式（bug : List Size = 1 默认对象的解析方式，demo: salary size=1 salary.improve, salary.basic 解析方式）
 2 binary type, java string。
 3 Boolean type, java Boolean
 4 Date type， java string 类型， 通过Handler 转换的实现方式。
 5 geo point type, java string list object 不同的数据映射方式
 6 number （byte short integer long float double） 转换默认 Integer， double 通过Handler 过滤方式
 ``` 
##### search-demo
- demo 代码示例， 测试 search-client 内容
- ```SqlSupportTest```  es 对SQL 的种类测试。
#### es 集成mybatis
- es datasource connection 没有实现 Statement 不支持JdbcTemplate 相关的操作方式
#### sql 测试
```
SQL Select : select * from teacher
SQL Order By : select * from teacher order by id
SQL Group By : select name, count(name) from teacher group by name
SQL AND & OR : select * from teacher where id = 1 or age > 20
SQL Like : select * from teacher where name like "xiao%"
SQL In : select * from teacher where id in (1 , 2, 4)
SQL Between : select * from teacher where id between 5 and 8
SQL avg last max min sum
	: select age, count(age), avg(id), max(id), min(id), sum(id)  from teacher group by age
SQL null : select * from teacher where name = null
```
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

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "11" } }
{"id": "11", "name": null, "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "12" } }
{"id": "12", "name": null, "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "13" } }
{"id": "13", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "14" } }
{"id": "14", "age": 20, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "15" } }
{"id": "15", "name": "zhetan", "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "16" } }
{"id": "16", "age" : null, "name": "zhetan", "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}
```