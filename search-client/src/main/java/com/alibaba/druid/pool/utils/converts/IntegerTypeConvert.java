package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class IntegerTypeConvert implements SingleTypeConvert<Integer>{
    @Override
    public Integer convertType(Object o) {
        return Integer.valueOf(o.toString());
    }
}
