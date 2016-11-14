package com.qingqing.search.client.handler;

import com.google.common.collect.Lists;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoqijun on 2016/11/14.
 * T : 对应的List Bean 的数据类型
 */
public abstract class ListAbstractTypeHandler<T> extends BaseTypeHandler<List> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        // set List type object
        ps.setObject(i, parameter);
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs.getObject(columnName));
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs.getObject(columnIndex));
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getNullableResult(cs.getObject(columnIndex));
    }

    private List<T> getNullableResult(Object o){
        if(o instanceof List){
            List beans = (List) o;
            List<T> results = new ArrayList<>(beans.size());
            for (Object  bean : beans) {
                results.add(listBeanTypeConvert(bean));
            }
            return results;
        }
        return null;
    }

    protected abstract T listBeanTypeConvert(Object bean);
}
