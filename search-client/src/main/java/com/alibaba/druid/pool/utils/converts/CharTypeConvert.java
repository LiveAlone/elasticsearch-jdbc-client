package com.alibaba.druid.pool.utils.converts;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class CharTypeConvert implements SingleTypeConvert<Character>{
    @Override
    public Character convertType(Object o) {
        return o.toString().charAt(0);
    }
}
