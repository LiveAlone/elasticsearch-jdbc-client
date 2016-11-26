package com.qingqing.search.client.handler;

import com.google.common.collect.Lists;
import com.qingqing.search.client.exception.EsHandlerException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public abstract class ObjectAbstractHandler<T> extends BaseTypeHandler<T>{

    private static final String SPLIT_DOT = ".";

    private List<String> fields;    // className.field 字段列举

    public ObjectAbstractHandler(){
        Type t = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) t).getActualTypeArguments();
        Class<T> cls = (Class<T>) params[0];
        fields = analyseBeanContent(cls);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertObject(rs);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertObject(rs);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    private T convertObject(ResultSet rs) throws SQLException{
        if(fields == null){
            throw new EsHandlerException("object type handler error");
        }

        Map<String, Object> resultMap = new HashMap<>(fields.size());
        for (String field : fields) {
            resultMap.put(field.substring(field.indexOf(SPLIT_DOT) + 1), rs.getObject(field));
        }

        return convertInnerObject(resultMap);
    }

    protected abstract T convertInnerObject(Map<String, Object> map);

    private static List<String> analyseBeanContent(Class clz){
        List<String> result = Lists.newArrayList();

        String className = clz.getSimpleName();

        for (Field field : clz.getDeclaredFields()) {
            result.add(className.toLowerCase() + SPLIT_DOT + field.getName().toLowerCase());
        }

        return result;
    }
}

