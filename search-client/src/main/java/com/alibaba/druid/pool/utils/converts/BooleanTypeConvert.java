package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class BooleanTypeConvert implements SingleTypeConvert<Boolean>{
    @Override
    public Boolean convertType(Object o) {
        return Boolean.valueOf(o.toString());
    }
}
