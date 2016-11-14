package com.qingqing.search.demo.handler;

import com.qingqing.search.client.handler.ListAbstractTypeHandler;
import com.qingqing.search.demo.domain.Salary;

import java.util.Map;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public class ListSalaryHandler extends ListAbstractTypeHandler<Salary>{

    @Override
    protected Salary listBeanTypeConvert(Object bean) {
        Map<String, Integer> beanType = (Map) bean;

        Integer basic = beanType.get("basic");
        Integer improve = beanType.get("improve");

        return new Salary(basic, improve);
    }
}
