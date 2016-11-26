package com.alibaba.druid.pool.utils;

import com.alibaba.druid.pool.utils.converts.*;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-11-26
 * Email:yaoqijunmail@gmail.io
 * Descirbe: es 官方Client , 返回Object 类型不一定对, Es -> MyBatis Type 转换方式
 */
public class ResultConvert {

    private static final Logger logger = LoggerFactory.getLogger(ResultConvert.class);

    private static final Map<Class, SingleTypeConvert> convertMap = Maps.newHashMap();

    static {
        // init map
        convertMap.put(Byte.class, new ByteTypeConvert());
        convertMap.put(Short.class, new ShortTypeConvert());
        convertMap.put(Integer.class, new IntegerTypeConvert());
        convertMap.put(Long.class, new LongTypeConvert());
        convertMap.put(Boolean.class, new BooleanTypeConvert());
        convertMap.put(Float.class, new FloatTypeConvert());
        convertMap.put(Double.class, new DoubleTypeConvert());
        convertMap.put(Character.class, new CharTypeConvert());
        convertMap.put(Date.class, new DateTypeConvert());
        convertMap.put(Timestamp.class, new TimestampTypeConvert());
        convertMap.put(Time.class, new TimeTypeConvert());
        convertMap.put(byte[].class, new BytesTypeConvert());
    }

    /**
     * 转换成特定类型的数据
     * @param o
     * @param targetClass
     * @return
     */
    public static Object convertTargetValue(Object o, Class targetClass){
        if(null == o){
            return null;
        }

        // already target class return
        if (o.getClass().equals(targetClass)){
            return o;
        }

        Class originClass = o.getClass();
        if(convertMap.containsKey(targetClass)){
            try {
                return convertMap.get(targetClass).convertType(o);
            }catch (Exception e){
                logger.error("convert type error, originClass:{}, targetClass:{}, e:{}", originClass, targetClass, e);
                return null;
            }

        }else {
            logger.error("es query class type not found, originClass:{}, targetClass:{}", originClass, targetClass);
            return null;
        }
    }
}
