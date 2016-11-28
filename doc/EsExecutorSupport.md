#### Es executor support
##### delete executor support
 - sql 转换的方式， 1.7 DeleteByQuery 官方不支持了， 当前通过Id query 后删除的方式。
 - delete sql
 ```
	 delete from master where age = 20
	 返回结果Null
 ```
