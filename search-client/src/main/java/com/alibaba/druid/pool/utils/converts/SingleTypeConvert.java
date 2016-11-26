package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe: 类型转换方式
 */
public interface SingleTypeConvert<T> {

    T convertType(Object o);

}
