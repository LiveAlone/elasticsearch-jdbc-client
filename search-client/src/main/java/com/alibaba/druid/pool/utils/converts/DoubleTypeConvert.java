package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class DoubleTypeConvert implements SingleTypeConvert<Double>{
    @Override
    public Double convertType(Object o) {
        return Double.valueOf(o.toString());
    }
}
