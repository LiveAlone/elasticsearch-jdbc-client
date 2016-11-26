package com.alibaba.druid.pool.utils.converts;


import java.sql.Date;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class DateTypeConvert implements SingleTypeConvert<Date>{

    @Override
    public Date convertType(Object o) {
        return Date.valueOf(o.toString());
    }
}
