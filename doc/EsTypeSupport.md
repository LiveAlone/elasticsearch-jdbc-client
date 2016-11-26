#### Es 不同的数据类型的支持
##### Es Result 不同类型判定方式
1. mybatis 获取对象类型
	- JdbcType 类型
	- java bean class 类型的获取方式（通过Field 类型GetResult 方式获取）
2. 构造 Es 返回类型， result set 类型的转换方式。

##### 测试Mapping 数据结构
 - Core 数据类型： string byte/short/integer/long float/double boolean null 数据类型
 - 数据类型：date,  binary， array(List),  object (Nested),  geo 
```
{
  "master" : {
    "mappings" : {
      "master_info" : {
        "properties" : {
          "age" : {
            "type" : "integer"
          },
          "grade" : {
            "type" : "short"
          },
          "height" : {
            "type" : "float"
          },
          "id" : {
            "type" : "long"
          },
          "name" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "salary" : {
            "type" : "nested",
            "properties" : {
              "basic" : {
                "type" : "double"
              },
              "improve" : {
                "type" : "double"
              }
            }
          },
          "sex" : {
            "type" : "byte"
          },
          "weight" : {
            "type" : "double"
          }
        }
      }
    }
  }
}
```
##### 测试数据内容Content
```
{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "1" } }
{"weight": 100.10, "height": 90.0, "sex": 0, "id": "1", "grade": 1, "name": "xiaoming", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "2" } }
{"weight": 100.10, "height": 90.0, "sex": 0, "id": "2", "grade": 1, "name": "xiaohong", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "3" } }
{"weight": 100.10, "height": 90.0, "sex": 0, "id": "3", "grade": 1, "name": "xiaohua", "age": 10, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "4" } }
{"weight": 100.10, "height": 90.0, "sex": 0, "id": "4", "grade": 1, "name": "zhanghan", "age": 20, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "5" } }
{"weight": 100.10, "height": 90.0, "sex": 0, "id": "5", "grade": 1, "name": "zhetan", "age": 20, "salary": [{"basic": 100,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "6" } }
{"weight": 100.231, "height": 45.23, "sex" : 1, "id": "6", "grade": 2, "name": "zhicao", "age": 20, "salary": [{"basic": 50,"improve": 20},{"basic": 90,"improve": 20},{"basic": 12,"improve": 24}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "7" } }
{"weight": 100.231, "height": 45.23, "sex" : 1, "id": "7", "grade": 2, "name": "shaolong", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 100,"improve": 20},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "8" } }
{"weight": 100.231, "height": 45.23, "sex" : 1, "id": "8", "grade": 2, "name": "jinhua", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 10,"improve": 210},{"basic": 100,"improve": 201}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "9" } }
{"weight": 100.231, "height": 45.23, "sex" : 1, "id": "9", "grade": 2, "name": "xiaohuang", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 100,"improve": 220},{"basic": 100,"improve": 20}]}

{ "index" : { "_index" : "teacher", "_type" : "teacher_info", "_id" : "10" } }
{"weight": 100.231, "height": 45.23, "sex" : 1, "id": "10", "grade": 2, "name": "qijun", "age": 30, "salary": [{"basic": 50,"improve": 20},{"basic": 100,"improve": 230},{"basic": 100,"improve": 10}]}
```