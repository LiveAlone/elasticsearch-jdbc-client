package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class LongTypeConvert implements SingleTypeConvert<Long>{
    @Override
    public Long convertType(Object o) {
        return Long.valueOf(o.toString());
    }
}
