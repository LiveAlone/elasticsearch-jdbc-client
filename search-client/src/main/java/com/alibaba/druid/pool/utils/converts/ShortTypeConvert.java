package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class ShortTypeConvert implements SingleTypeConvert<Short>{
    @Override
    public Short convertType(Object o) {
        return Short.valueOf(o.toString());
    }
}
