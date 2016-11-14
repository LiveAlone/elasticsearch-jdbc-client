package com.qingqing.search.demo.handler;

import com.qingqing.search.client.handler.ObjectAbstractHandler;
import com.qingqing.search.demo.domain.Name;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public class NameObjectHandler extends ObjectAbstractHandler<Name>{

    @Override
    protected Name convertInnerObject(Map<String, Object> map) {
        return new Name(String.valueOf(map.get("first")), String.valueOf(map.get("last")));
    }
}
