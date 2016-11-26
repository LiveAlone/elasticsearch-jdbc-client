package com.alibaba.druid.pool.utils.converts;

import java.sql.Timestamp;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class TimestampTypeConvert implements SingleTypeConvert<Timestamp> {
    @Override
    public Timestamp convertType(Object o) {
        return Timestamp.valueOf(o.toString());
    }
}
