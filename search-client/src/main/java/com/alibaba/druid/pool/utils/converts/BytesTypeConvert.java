package com.alibaba.druid.pool.utils.converts;


import com.google.common.primitives.Bytes;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class BytesTypeConvert implements SingleTypeConvert<byte[]>{
    @Override
    public byte[] convertType(Object o) {
        return o.toString().getBytes();
    }
}
