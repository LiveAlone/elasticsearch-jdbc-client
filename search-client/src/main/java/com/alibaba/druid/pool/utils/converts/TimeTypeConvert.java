package com.alibaba.druid.pool.utils.converts;

import java.sql.Time;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class TimeTypeConvert implements SingleTypeConvert<Time>{
    @Override
    public Time convertType(Object o) {
        return Time.valueOf(o.toString());
    }
}
