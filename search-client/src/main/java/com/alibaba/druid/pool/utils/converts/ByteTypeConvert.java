package com.alibaba.druid.pool.utils.converts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class ByteTypeConvert implements SingleTypeConvert<Byte>{

    @Override
    public Byte convertType(Object o) {
        return Byte.valueOf(o.toString());
    }
}
