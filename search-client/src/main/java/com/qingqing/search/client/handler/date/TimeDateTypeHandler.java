package com.qingqing.search.client.handler.date;

import com.qingqing.search.client.handler.DateAbstractTypeHandler;
import org.apache.ibatis.type.DateTypeHandler;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public class TimeDateTypeHandler extends DateAbstractTypeHandler{
    @Override
    public String getDateFormat() {
        return "yyyy-MM-dd HH:mm:ss";
    }
}
