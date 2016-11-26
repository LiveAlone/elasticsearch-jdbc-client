package com.alibaba.druid.pool.utils.converts;

import javax.xml.crypto.dsig.SignatureMethod;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class FloatTypeConvert implements SingleTypeConvert<Float>{
    @Override
    public Float convertType(Object o) {
        return Float.valueOf(o.toString());
    }
}
